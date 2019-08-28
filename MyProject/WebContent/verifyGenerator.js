/**
 * 
 */
const byId = (id) => document.getElementById(id);
let display = "";

let randomNumber = Math.floor(Math.random() * 9999 + 1);

Number.prototype.pad = function(size) {
	  let s = String(this);
	  while (s.length < (size || 2)) {s = "0" + s;}
	  return s;
}

let num = randomNumber.pad(4);

for(let i = 0 ; i < 4 ; i++){
		
	display += `<img src=images/${num[i]}.jpg>`;
}

byId("correct_number").value = randomNumber;
byId("verifyNumber").innerHTML = display;