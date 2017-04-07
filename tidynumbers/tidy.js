

function findLastTidy(n) {
  if (n < 1) {
    console.error("we got a number under 1", n);
    return false;
  }

  let lastTidy = n;
  while (!isTidy(n)) {
    n--;
    lastTidy = n;
  }

  return lastTidy;
}

function isTidy(n) {
  // all single digit elements are already tidy :) (cheater!!!)
  if (n.length == 1) {
    return true;
  }

  let nums = n + "".split("");

  for (let indx = 1; indx < nums.length; indx++) {
    const itm = nums[indx];
    if (itm < nums[indx - 1]) {
      // no tidy :(
      return false;
    }
  }

  return true;
}

// testing section ::::::::::::::::::::::: BEGINS

// console.log("last tidy is", findLastTidy("111111111111111110"));

// testing section ::::::::::::::::::::::: ENDS


function main () {

    const fs = require('fs');
    const input = 'input.in';
    fs.readFile(input, 'utf-8', (err, data) => {
      if (err) {
        console.error('Could not read file', input, 'due to', err);
        return false;
      }

      const cases = data.split("\n");
      let numberCases = cases[0];

      let solutions = "";
      for (let indx = 1; indx <= numberCases; indx++) {
        const currentCase = cases[indx];
        // console.log('current case', currentCase);
        solutions += "Case #" + indx + ": " + findLastTidy(currentCase) + "\n";
      }

      fs.writeFile('output', solutions, 'utf-8', (err) => {
        if (err) {
          console.error('Could not write to file! :(', err);
        }
        console.log('Saved output!');
      });
    });
}

main();
