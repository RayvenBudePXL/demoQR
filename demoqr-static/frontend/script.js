function generateQr() {
    const input = document.getElementById("qrInput").value;

    if (!input) {
        alert("Voer eerst tekst in");
        return;
    }

    const img = document.getElementById("qrImage");

    // Simpelste manier: direct URL zetten
    img.src = `http://localhost:8080/qr?data=${encodeURIComponent(input)}`;
}