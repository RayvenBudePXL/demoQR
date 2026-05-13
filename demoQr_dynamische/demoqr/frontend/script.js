function generateQr() {
    const img = document.getElementById("qrImage");

    function updateQR() {
        img.src = `http://127.0.0.1:8080/qr?t=${Date.now()}`;
    }
    fetch("http://localhost:8080/qr")
        .then(r => console.log("OK"))
        .catch(e => console.log(e));
    updateQR();
    setInterval(updateQR, 20000); // elke 30 sec nieuwe token
}