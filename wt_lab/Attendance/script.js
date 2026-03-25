let xmlDoc;
const parser = new DOMParser();
const serializer = new XMLSerializer();
const today = new Date().toLocaleDateString();

// Initial Data Seed
let xmlString = `
<attendance>
    <student><name>Sai Krishna</name><history></history></student>
    <student><name>Anjali Rao</name><history></history></student>
    <student><name>Arjun V</name><history></history></student>
</attendance>`;

document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('current-date').innerText = today;
    xmlDoc = parser.parseFromString(xmlString, "text/xml");
    populateSuggestions();
});

function populateSuggestions() {
    const names = xmlDoc.getElementsByTagName("name");
    const datalist = document.getElementById('student-suggestions');
    datalist.innerHTML = '';
    Array.from(names).forEach(n => {
        let option = document.createElement('option');
        option.value = n.textContent;
        datalist.appendChild(option);
    });
}

function markAttendance() {
    const input = document.getElementById('studentSearch');
    const targetName = input.value.trim();
    const students = xmlDoc.getElementsByTagName("student");
    let found = false;

    for (let s of students) {
        let nameNode = s.getElementsByTagName("name")[0];
        if (nameNode.textContent.toLowerCase() === targetName.toLowerCase()) {
            let history = s.getElementsByTagName("history")[0];
            
            // Check if already marked
            let entries = history.getElementsByTagName("entry");
            for(let e of entries) {
                if(e.textContent === today) {
                    alert("Attendance already marked for today!");
                    return;
                }
            }

            let newEntry = xmlDoc.createElement("entry");
            newEntry.textContent = today;
            history.appendChild(newEntry);
            
            logActivity(`${nameNode.textContent} marked Present.`);
            found = true;
            input.value = '';
            break;
        }
    }

    if (!found) alert("Student not found in database.");
}

function logActivity(msg) {
    const log = document.getElementById('activity-log');
    const li = document.createElement('li');
    li.textContent = `[${new Date().toLocaleTimeString()}] ${msg}`;
    log.prepend(li);
}

function downloadXML() {
    const updatedXML = serializer.serializeToString(xmlDoc);
    const blob = new Blob([updatedXML], { type: "text/xml" });
    const url = window.URL.createObjectURL(blob);
    const a = document.createElement('a');
    
    a.href = url;
    a.download = `Attendance_Report_${today.replace(/\//g, '-')}.xml`;
    document.body.appendChild(a);
    a.click();
    window.URL.revokeObjectURL(url);
    document.body.removeChild(a);
}