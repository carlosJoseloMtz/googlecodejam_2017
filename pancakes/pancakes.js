

function resolvePancakes(pcks) {
  // console.log('pancakes', pcks);
  var pile = pcks.split("");

  if (isAllFine(pile)) {
    return 0;
  }

  let steps = 0;

  // as optimization we check if most of the pancakes are upside down (:))
  if (mostOfThemAreUpsideDown(pile)) {
    pile = turnPile(pile);
    steps++;
  }

  while (!isAllFine(pile)) {
    let range = calculatePile(pile);

    let aux = [];
    for (let indx = range[0]; indx <= range[1]; indx++) {
      aux.push(pile.shift());
    }
    let nPile = turnPile(aux);
    nPile.forEach((itm) => {
      pile.splice(0, 0, itm);
    });

    steps++;
  }
  // console.log('steps', steps);
  // console.log(pile);
  return steps;
}

function isAllFine(pile) {
  return pile.every((itm) => {
    return (itm === '+');
  });
}

function calculatePile(pile) {
  let top = 0;
  let bottom = 0;
  let firstItm = pile[top];
  // go through the elements in the array to find where the symbol changes
  for (let indx = 1; indx < pile.length; indx++) {
    if (pile[indx] !== firstItm) {
      break;
    }
    bottom = indx;
  }

  // worst case scenario, this will go through all the items and will return both head and tail
  // console.log('should move from', top, 'to', bottom);
  return [top, bottom];
}

function mostOfThemAreUpsideDown(pile) {
  let count = 0;
  pile.forEach((itm, indx) => {
    if (itm === '-') {
      count++;
    }
  });

  return pile.length / 2 < count;
}

function turnPile(pcks) {
  // revese the pile
  let _pile = pcks.reverse();
  // transform the symbols (turn them Xside up / down)
  _pile.forEach((itm, indx) => {
    _pile[indx] = itm === '-' ? '+' : '-';
  });

  return _pile;
}


// resolvePancakes("+-+----+++---++");

function main (debug) {
  const fs = require('fs');
  const input = 'pancakes_small';
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
      const appender = debug ? ' ' + currentCase + ' ' : '';
      solutions += "Case #" + indx + ": " + resolvePancakes(currentCase) + appender + "\n";
    }

    fs.writeFile('output_pancakes', solutions, 'utf-8', (err) => {
      if (err) {
        console.error('Could not write to file!', err);
      }
      console.log('Saved output!');
    });
  });

}


// console.log('steps', resolvePancakes('++-++++---'));

const runTest = true;

if (runTest) {
  main(false);
}
