function factors() {
  let number = document.getElementById("value").value;
  const xhttp = new XMLHttpRequest();
  xhttp.onload = function () {
    document.getElementById("getfactors").innerHTML = this.responseText;
  };
  xhttp.open("GET", "/factors?value=" + number);
  xhttp.send();
}

function primes() {
  let number = document.getElementById("val_prime").value;
  const xhttp = new XMLHttpRequest();
  xhttp.onload = function () {
    document.getElementById("getprimes").innerHTML = this.responseText;
  };
  xhttp.open("GET", "/primes?value=" + number);
  xhttp.send();
}
