d = {
  "a": 5,
  "b": 7,
  "c": 10,
  "d": 10,
  "e": 2
}

out = [[]] * (max(d.values()))

for key, value in d.items():
  out[value - 1].append(key)
  print(out)

out = [sorted(keys) for keys in out]

print(sum(out, []))
