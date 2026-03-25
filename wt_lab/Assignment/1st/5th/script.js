function validateForm() {
    let form = document.forms["regForm"];
    
    // 1. Name Validation 
    if (form["name"].value.trim() == "") {
        alert("Name must be filled out");
        return false;
    }

    // 2. Age Validation 
    let age = form["age"].value;
    if (age === "" || isNaN(age) || age < 18 || age > 100) {
        alert("Please enter a valid age between 18 and 100");
        return false;
    }

    // 3. Address Validation 
    if (form["address"].value.length < 10) {
        alert("Address must be at least 10 characters long");
        return false;
    }

    // 4. Email Validation 
    let email = form["email"].value;
    let emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    if (!emailPattern.test(email)) {
        alert("Invalid email format");
        return false;
    }

    // 5. Hobby Checkbox Validation 
    let hobbies = document.querySelectorAll('input[name="hobby"]:checked');
    if (hobbies.length == 0) {
        alert("Please select at least one hobby");
        return false;
    }

    // 6. Gender Radio Validation 
    if (form["gender"].value == "") {
        alert("Please select your gender");
        return false;
    }

    // 7. Country Dropdown Validation 
    if (form["country"].value == "") {
        alert("Please select a country");
        return false;
    }

    alert("Form submitted successfully!");
    return true;
}