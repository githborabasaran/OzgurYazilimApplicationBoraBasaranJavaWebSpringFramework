function createReport() {
    var fileNumber = document.getElementById("fileNumber").value;
    var patientName = document.getElementById("patientName").value;
    var diagnosisTitle = document.getElementById("diagnosisTitle").value;
    var diagnosisDetails = document.getElementById("diagnosisDetails").value;

    var report = {
        fileNumber: fileNumber,
        patientName: patientName,
        diagnosisTitle: diagnosisTitle,
        diagnosisDetails: diagnosisDetails
        // Add other properties as needed
    };

    fetch('/api/reports', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(report)
    })
        .then(response => response.json())
        .then(data => {
            // Log the response data to the console
            console.log('API Response:', data);

            // Update the table with the new report
            var reportTableBody = document.getElementById("reportTableBody");
            var newRow = reportTableBody.insertRow();
            newRow.innerHTML = `<td>${data.fileNumber}</td><td>${data.patientName}</td><td>${data.diagnosisTitle}</td><td>${data.diagnosisDetails}</td>`;
        })
        .catch(error => {
            // Handle errors, e.g., display an error message to the user
            console.error('Error:', error);
        });
}