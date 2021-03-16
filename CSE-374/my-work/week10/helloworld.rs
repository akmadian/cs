use std::io;

fn read_line() -> String {
    let mut result = String::new();
    /*
        io::stdin().read_line(&mut result)?;
        combined with fn read_line() -> io::Result<String> signature means
        "Return result, or error if io.read_line returns error"
        Basically, allows our fn to return an error or the result

        if no error, we use Ok(result) for returning
    */
    io::stdin().read_line(&mut result).expect("failed to read line");
    result
}

fn read_ten_lines() -> Vec<String> {
    let mut result: Vec<String> = Vec::new();
    // _ automatically silences unused var compiler warning
    for _ in 0..10 {
        result.push(read_line());
    }
    // Can return by just stating var name
    result
}

fn main() {
    println!("Hello, world!");
    //let line = read_line();
    //print!("{}", line);

    let lines = read_ten_lines();
    for line in lines {
        // print! is kinda like printf
        print!("{}", line);
    }
}

