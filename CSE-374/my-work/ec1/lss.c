#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <dirent.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <math.h>
#include <string.h>
#include <time.h>
#include <pwd.h>
#include <grp.h>
#include <locale.h>
#include <wchar.h>

#define ORANGE "\033[0;36m"
#define BLUE   "\033[1;34m"
#define GREEN  "\033[0;32m"

#define BOLD "\033[1m"
#define NS   "\033[0m"

int RECURSE = 0;

/*!
 * \brief Returns a string representing the type of a file
 * \param st the stat struct for the file
 */
char* fType (struct stat st) {
  switch ( st.st_mode & S_IFMT ) {
    case S_IFBLK:  return "block device";
    case S_IFCHR:  return "character device";
    case S_IFDIR:  return "directory";
    case S_IFIFO:  return "FIFO/pipe";
    case S_IFLNK:  return "symlink";
    case S_IFREG:  return "regular file";
    case S_IFSOCK: return "socket";
    default:       return "unknown";
  }
}

wchar_t* recurseTreeStructure(int depth) {
  wchar_t angle = L'└';
  wchar_t vert  = L'│';
  wchar_t flat  = L'─';
  wchar_t T     = L'├';
  wchar_t space = L' ';
  wchar_t* out;
  out = (wchar_t *) malloc((unsigned long) ((depth * 2) + 4) * sizeof(wchar_t));
  out[0] = T;

  if (depth == 0) {
    out[1] = flat;
    out[2] = space;
  } else {
    out[0] = vert;

    int leftpad = 1;
    for (; leftpad < (depth * 2) + 1; leftpad++) {
      out[leftpad] = space;
    }
    out[(depth * 2) + 1] = angle;
    out[(depth * 2) + 2] = flat;
    out[(depth * 2) + 3] = flat;
  }
  //out[(depth * 2) + 4] = L'\0';

  return out;
}

/*!
 * \breif Formats a file name with the proper styling
 * \param fname is the name of the file
 * \param type is the file type
 * \param fullpath is the full path to the file
 * \param isExecutable is a boolean indicating whether or not the file is executable
 */
char* formatfname (char* fname, char* ftype, char* fullpath, int isExecutable) {
  static char formattedbuf[100];

  // For symlinks, there's some extra work to be done, mostly with formatting the link target
  if (strcmp(ftype, "symlink") == 0) {
    struct stat st_;
    char targetloc[100];
    int isExecutable_ = 0;

    realpath(fullpath, targetloc);
    stat(targetloc, &st_);
    if (st_.st_mode & S_IXUSR)
      isExecutable_ = 1;

    char* formattedTargetName = formatfname(targetloc, fType(st_), targetloc, isExecutable_);

    snprintf(formattedbuf, 100, "%s%s%s%s -> %s",
      ORANGE, BOLD, fname, NS, formattedTargetName
    );

    //free(formattedTargetName);
  }
  else if (strcmp(ftype, "directory") == 0) { snprintf(formattedbuf, 100, "%s%s%s%s/", BLUE, BOLD, fname, NS); }
  else if (strcmp(ftype, "regular file") == 0) {
    if (isExecutable)
      snprintf(formattedbuf, 100, "%s%s%s%s*", GREEN, BOLD, fname, NS);
    else
      snprintf(formattedbuf, 100, "%s", fname);
  }
  else { snprintf(formattedbuf, 100, "%s", fname); }

  return formattedbuf;
}

char* sizeString(char* path) {
  FILE *fp;
  char command[250];
  strcpy(command, "du -sh --apparent-size ");
  strcat(command, path);
  strcat(command, " 2>/dev/null | tail -n1 | awk '{print $1}'");
  static char res[10];
  fp = popen(command, "r");
  fscanf(fp, "%s", res);
  fclose(fp);

  return res;
}

/*!
 * \ breif Generates the output for some file or dir.
 * \param dir is the path to the name of the file, including the file name.
 * \param fname is the name of the file itself.
 */
char* genEntry(char* dir, char* fname, int depth) {
  struct stat st;
  char fullname[250];
  strcpy(fullname, dir);
  strcat(fullname, "/");
  strcat(fullname, fname);

  lstat(fullname, &st);
  int isExecutable;
  if (st.st_mode & S_IXUSR)
    isExecutable = 1;
  else
    isExecutable = 0;

  char* ftype = fType(st);
  char* formattedName = formatfname(fname, ftype, fullname, isExecutable);
  char* formattedSize;

  struct passwd *pws;
  pws = getpwuid(st.st_uid);
  struct group *grp;
  grp = getgrgid(st.st_gid);

  if (strcmp(fname, ".") == 0 || strcmp(fname, "..") == 0)
    formattedSize = "____";
  else if (strcmp(ftype, "symlink") == 0) {
    struct stat st_;
    char targetloc[100];
    realpath(fullname, targetloc);
    stat(targetloc, &st_);
    char* targetType = fType(st_);

    if (strcmp(targetType, "directory") == 0) {
      formattedSize = sizeString(targetloc);
    } else {
      formattedSize = sizeString(fullname);
    }
  } else {
    formattedSize = sizeString(fullname);
  }
  //free(fullname);

  struct tm ts;
  char   timebuf[80];
  ts = *localtime(&st.st_mtime);
  strftime(timebuf, sizeof(timebuf), "%b %-2e %H:%M", &ts);

  wchar_t* recpad;
  if (RECURSE) {
    recpad = recurseTreeStructure(depth);
  } else {
    recpad = (wchar_t *) malloc(5);
    recpad[0] = L'\0';
  }

  static char entry[250];
  snprintf(entry, 250, "%-12s %7s %6s %6s %-14s %ls%s\n",
    ftype,
    formattedSize,
    pws->pw_name,
    grp->gr_name,
    timebuf,
    recpad,
    formattedName
  );

  free(recpad);

  //free(fullname);
  //free(formattedName);
  //if (strcmp(fname, ".") != 0 && strcmp(fname, "..") != 0)
    //free(formattedSize);

  return entry;
}

int nItemsInDir(char* path) {
  struct dirent *de;
  DIR *dir = opendir(path);
  int n =  0;

  while ((de = readdir(dir)) != NULL) {
    // If entry is a dir, and R flag is set, and not a dot dir
    if (de->d_type & DT_DIR && RECURSE && strcmp(de->d_name, ".") != 0 && strcmp(de->d_name, "..") != 0) {
      char sub[200];
      strcpy(sub, path);
      strcat(sub, "/");
      strcat(sub, de->d_name);
      strcat(sub, "\0");

      //printf("ADD: %s | %s\n", path, de->d_name);
      // Count files in subdir
      n = n + nItemsInDir(sub);
    }
    // Don't count . and .. dirs
    if (strcmp(de->d_name, ".") != 0 && strcmp(de->d_name, "..") != 0) {
      n++;
    }
  }
  closedir(dir);

  return n;
}

void entriesForDir(char* path, char *entries, int depth) {
  struct dirent *de;
  DIR *dir = opendir(path);
  while ((de = readdir(dir)) != NULL) {
    if (strcmp(de->d_name, ".") == 0 || strcmp(de->d_name, "..") == 0)
      continue;

    char* entry = genEntry(path, de->d_name, depth);
    strcat(entries, entry);
    //free(entry);

    if (de->d_type & DT_DIR && RECURSE && strcmp(de->d_name, ".") != 0 && strcmp(de->d_name, "..") != 0) {
      char sub[250];
      strcpy(sub, path);
      strcat(sub, "/");
      strcat(sub, de->d_name);
      strcat(sub, "\0");

      entriesForDir(sub, entries, depth + 1);
    }
  }
  closedir(dir);
}

void printargs(int argc, char* argv[]) {
  int i;
  for (i = 0; i < argc; i++) {
    printf("%i %s\n", i, argv[i]);
  }
}

void printhelp(char* name) {
  fprintf(stderr, "Usage: %s [-hd] [directory...]\n", name);
  exit(EXIT_FAILURE);
}

int main (int argc, char* argv[]) {
  setlocale(LC_CTYPE, "");
  int VERBOSE = 0;
  //int DOTDIRS = 0;

  int opt;
  while ((opt = getopt(argc, argv, ":hdvRD")) != -1) {
    switch (opt) {
      case 'h': printhelp(argv[0]); break;
      case 'd': printargs(argc, argv); break;
      case 'v': VERBOSE = 1; break;
      case 'R': RECURSE = 1; break;
      default: printhelp(argv[0]); break;
    }
  }


  char* dir;
  if (optind < argc) {
    dir = (char *) malloc(strlen(argv[optind]) + 1);
    strcpy(dir, argv[optind]);
  } else {
    dir = (char *) malloc(sizeof("."));
    strcpy(dir, ".");
  }

  int n = nItemsInDir(dir);

  char* dot = genEntry(dir, ".", 0);
  char* dotdot = genEntry(dir, "..", 0);
  char ENTRIES[n * 250];
  strcpy(ENTRIES, "");
  strcat(ENTRIES, dot);
  strcat(ENTRIES, dotdot);
  entriesForDir(dir, ENTRIES, 0);

  char* rootsize = sizeString(dir);
  printf("Total Size of %s : %s, (%i items)\n", dir, rootsize, n);

  printf("%-12s %7s %6s %6s %-14s %s\n", "Type", "Size", "Owner", "Group", "Last Modified", "Name");
  printf("%s", ENTRIES);

  free(dir);

  return 0;
}


