// ============================================regstration js code started===========================================

var originalUserId = "";
var originalPassword = "";
var swiper;
function registration() {
    let form = `
    <div class="registration-form-container">
    <button  onclick="closeRegistrationForm()" class="close-button">X</button>
    <h2 class="registration-form-heading">Registration Form</h2>
        <input type="text" id="name" name="name" required class="registration-inputs-fields" placeholder="Enter Full Name:"><br>
    
        <input type="email" id="email" name="email" placeholder="Enter email" required class="registration-inputs-fields"><br>
        
        <input type="password" id="password" name="email"placeholder="Enter Password:" required class="registration-inputs-fields"><br>

        <input type="password" id="cPassword" name="email"placeholder="Confirm Password:" required class="registration-inputs-fields"><br>

        <button value="Submit" class="registration-form-submit-btn"onclick="registerationFun()">Register</button>
</div>

  `;
    document.getElementById("registration-form").innerHTML = form;
    document.getElementById("registration-form").style.display = "block";
    document.getElementById("main-body").style.pointerEvents = "none";
}

function closeRegistrationForm() {

    document.getElementById("registration-form").innerHTML = "";
    document.getElementById("registration-form").style.display = "none";
    document.getElementById("main-body").style.pointerEvents = "all";
}


// =====================================================2nd part========================================================

function registerationFun() {

//taking the value of registration form
    var name = $("#name").val();
    var email = $("#email").val();
    var password = $("#password").val();
    var cPassword = $("#cPassword").val();
    closeRegistrationForm();
    // chacking the value of the registariong for validating the user

    var result = validateRegistraiongInputs(name, email, password, cPassword);
    if (result === "") {
// Create an object with the input data for registration
        var registrationData = {
            name: name,
            email: email,
            password: password,
            cPassword: cPassword,
        };
        // sending the data to servlet for registration the user

        $.ajax({
            url: "EnotesRegistrationServlet", // my Registration servlet
            type: "POST",
            data: registrationData,
            success: function (response) {
                if (response == "registered")
                {
                    Swal.fire({
                        title: "Success!",
                        text: "Registration successfull!!",
                        icon: "success",
                        showConfirmButton: false,
                        timer: 1000 // Display the message for 1 second
                    }).then(() => {
                        login();
                    });
                } else if (response == "server issues") {

                    Swal.fire({
                        title: "Sorry!",
                        text: "Due to server issue can't registered please try again!!",
                        icon: "error",
                        showConfirmButton: false,
                        timer: 2000 // Display the message for 1 second
                    }).then(() => {
                        registration();
                    });
                } else {

                    Swal.fire({
                        title: "Sorry!",
                        text: "Please Enter Unique password try again!!",
                        icon: "error",
                        showConfirmButton: false,
                        timer: 2000 // Display the message for 1 second
                    }).then(() => {
                        registration();
                    });
                }
            },
            error: function (xhr, status, error) {
                // Handle errors of the servlet response
                console.log("Request failed");
                console.log(error);
            },
        });
    } else {
        Swal.fire({
            title: "Sorry!",
            text: "Please Fill all the fields!!",
            icon: "error",
            showConfirmButton: false,
            timer: 1000 // Display the message for 1 second
        }).then(() => {
            registration();
        });
    }
}



// ==============================validateRegistraiongInputs function=========================================

function validateRegistraiongInputs(name, email, password, cPassword) {
    var errorStr = "Invalid ";
    if (name == "")
    {
        errorStr += "name, ";
    }
    if (email == "" || email[email.length - 4] != "." || email[email.length - 10] != "@")
    {
        errorStr += "email, ";
    }
    if (password == "")
    {
        errorStr += "passowrd, ";
    }

    if (cPassword == "")
    {
        errorStr += "cpassowrd, ";
    }
    if (password != cPassword)
        errorStr += "and password and cpasswrd not matching!"

    if (errorStr.length > 8)
        return errorStr;
    return "";
}

// ============================================regstration js code ended===========================================

// ============================================login js code started===========================================

function login() {

    let form = `
    <div class="login-form-container">
    <button class="close-button" onclick="closeLoginForm()">X</button>
    <h2 class="registration-form-heading login-form-heading">Login Form</h2>
        <input type="text" id="user-id" name="name" required class="registration-inputs-fields login-inputs-fields" placeholder="Enter Full Name:"required><br>
    
        <input type="password" id="user-id-password" name="password" placeholder="Enter Password" required class="registration-inputs-fields login-inputs-fields"required><br>

        <button value="Submit" class="registration-form-submit-btn login-form-submit-btn"onclick="loginFun()">Ligin</button>
</div>

  `;
    document.getElementById("login-form").innerHTML = form;
    document.getElementById("login-form").style.display = "block";
    document.getElementById("main-body").style.pointerEvents = "none";
}

function closeLoginForm() {
    document.getElementById("main-body").style.pointerEvents = "all";
    var form = document.querySelector(".login-form-container");
    form.style.display = "none";
}


function loginFun() {

    var name = $("#user-id").val();
    var password = $("#user-id-password").val();
    originalUserId = name;
    originalPassword = password;
    closeLoginForm();
    if (loginValidationFun(name, password) == true) {

        var loginData = {
            name: name,
            password: password
        };
        // sending the data to servlet for registration the user

        $.ajax({
            url: "EnotesUsersServlet",
            type: "POST",
            data: loginData,
            success: function (response) {
                if (response === "success") {
                    Swal.fire({
                        title: "Success",
                        text: "Login successfully!!",
                        icon: "success",
                        showConfirmButton: false,
                        timer: 1000  // Display the message for 2 seconds
                    });
                    var div = document.getElementById("nav-user");
                    name = name.charAt(0).toUpperCase() + name.slice(1);
                    div.innerHTML = ` <div class="user-img"><img src="image/Userimg.jpg" alt="User img"id="nav-user-img"></div>
                          <div class="user-name"><span id="nav-user-name">` + name + `</span></div>`

                    var div2 = document.getElementById("login-logout");
                    div2.innerHTML = `<button class="nav-link" onclick="logout()"><img src="image/login.png" alt="" class="nav-icon-logo nav-icon-logout">Logout</button>`

                    document.getElementById("nav-register-id").innerHTML = "";
                    var div3 = document.getElementById("nav-add-notes-id");
                    div3.innerHTML = `<button class="nav-link" onclick="addNotes()"><img src="image/addnotes.png" alt="" class="nav-icon-logo"> Add Notes</button>`;
                    var div4 = document.getElementById("nav-show-notes-id");
                    div4.innerHTML = `<button class="nav-link"onclick="showAllNotes()" ><img src="image/show notes.png" alt="" class="nav-icon-logo">Show Notes</button>`;

                    var showProfileDiv = document.getElementById("nav-show-profile-id");
                    showProfileDiv.innerHTML = `<button class="nav-link" onclick="showProfile()"><img src="image/profile.png" alt="" class="nav-icon-logo">Profile</button>`;

                } else {
                    Swal.fire({
                        title: "Sorry!",
                        text: "Please Enter valid User id and Password!!",
                        icon: "error",
                        showConfirmButton: false,
                        timer: 2000 // Display the message for 1 second
                    }).then(() => {
                        login();
                    });
                    // Handle error response
//                swal.fire("Sorry!", "Please Enter valid User id and password", "error")
                }
            },
            error: function (xhr, status, error) {
                // Handle AJAX error
                alert("AJAX error: " + error);
            }
        });
    } else {

        Swal.fire({
            title: "Sorry!",
            text: "Please Enter User id and Password!!",
            icon: "error",
            showConfirmButton: false,
            timer: 2000 // Display the message for 1 second
        }).then(() => {
            login();
        });
    }

}


function loginValidationFun(name, passowrd) {
    if (name != "" && passowrd != "")
        return true;
    return false;
}



function logout() {

    swal.fire({
        title: "Confirmation",
        text: "Are you sure you want to logout?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: 'OK',
        cancelButtonText: 'Cancel'
    }).then(function (confirmation) {
        if (confirmation.isConfirmed) {
            var div = document.getElementById("nav-user");
            div.innerHTML = "";
            var div2 = document.getElementById("login-logout");
            div2.innerHTML = `<button class="nav-link" onclick="login()"><img src="image/login.png" alt="" class="nav-icon-logo">Login</button>`;
            var div2 = document.getElementById("nav-register-id");
            div2.innerHTML = `<button class="nav-link" onclick="registration()"><img src="image/register.png" alt=""class="nav-icon-logo">Register</button>`;
            document.getElementById("nav-add-notes-id").innerHTML = "";
            document.getElementById("nav-show-notes-id").innerHTML = "";

            Swal.fire({
                title: "Logged Out",
                text: "You have been logged out successfully.",
                icon: "success",
                showConfirmButton: false,
                timer: 2000 // Display the message for 1 second
            });

        } else {
            Swal.fire({
                title: "Success!",
                text: "You are still Login",
                icon: "success",
                showConfirmButton: false,
                timer: 2000 // Display the message for 1 second
            });
        }
    });
}
// ============================================login js code ended===========================================


//=================================================profile started========================

function showProfile() {

    var titles = document.getElementsByClassName("title-input");
    var numberOfNotes = titles.length;
    var form = `<div class="login-form-container2">
        <button class="close-button2" onclick="closeProfileForm()">X</button>
        <h2 class="registration-form-heading login-form-heading2">Profile</h1>
        <div class="user-img"><img src="image/Userimg.jpg" alt=""id="user-img-id"></div>
       
        <div id="userid-notes">
           <h5 id="user-id2">Name :<span id="user-id-add"> ` + originalUserId + ` </span></h3>
           <h6 id="notes-id2">Number of Notes<span id="notes-id-add"> ` + numberOfNotes + ` </span></h4>
        </div>
    </div>`


    document.getElementById("profile-form").innerHTML = form;
    document.getElementById("profile-form").style.display = "block";
    document.getElementById("main-body").style.pointerEvents = "none";
}

function closeProfileForm() {
    document.getElementById("profile-form").innerHTML = "";
    document.getElementById("profile-form").style.display = "none";
    document.getElementById("main-body").style.pointerEvents = "all";
}
//==============================================profile Ended=============================
//========================notes adding====================================

function addNotes() {

    var form = ` <div class="container-fluid my-fluid-container">
       <div class="row myrow1">
        <div class="col-10"><input type="text"id="title"class="title-text"placeholder="Title:"required></div>
        <div class="col-2"><button id="content-close-btn-id"onclick="closeContentForm()">X</button></div>   
       </div>
      <div class="row myrow2">
          <div class="col"><input type="text"id="subject"class="title-text"placeholder="Subject:"required></div>
      </div>
      <div class="row myrow3">
        <div class="col">
          <textarea id="content" cols="30" rows="10"id="content" placeholder="Start Writing Notes:"required></textarea>  
        </div>
      </div>
      <div class="row myrow4">
          <div class="col"><button id="addingNotes"onclick="addingNotesFun()">Add</button></div>
      </div> 
   </div>
`;
    document.getElementById("add-notes-id").innerHTML = form;
    document.getElementById("add-notes-id").style.display = "block";
    document.getElementById("main-body").style.pointerEvents = "none";
}


function closeContentForm() {
    document.getElementById("main-body").style.pointerEvents = "all";
    document.getElementById("add-notes-id").innerHTML = "";
}


function addingNotesFun() {

    var title = $("#title").val();
    var subject = $("#subject").val();
    var content = $("#content").val();
    closeContentForm();
    if (validateInputsContent(title, subject, content) === true)
    {
        var notes = {
            name: originalUserId,
            title: title,
            subject: subject,
            content: content,
            password: originalPassword
        };
        $.ajax({
            url: "EnotesAddNotesServlet", // my addnotes servlet
            type: "POST",
            data: notes,
            success: function (response) {
                if (response == "success")
                {
                    Swal.fire({
                        title: "Success!",
                        text: "Note added successfully!",
                        icon: "success",
                        showConfirmButton: false,
                        timer: 2000 // Display the message for 1 second
                    });
                } else {
                    Swal.fire({
                        title: "Sorry!",
                        text: "Due to server issues note is not added!",
                        icon: "error",
                        showConfirmButton: false,
                        timer: 2000 // Display the message for 1 second
                    });
                }
            },
            error: function (xhr, status, error) {
                // Handle errors of the servlet response
                console.log("Request failed");
                console.log(error);
            },
        });
    } else {
        Swal.fire({
            title: "Sorry!",
            text: "Please Fill all the fields!!",
            icon: "error",
            showConfirmButton: false,
            timer: 1000 // Display the message for 1 second
        }).then(() => {
            addNotes();
        });
    }
}

function validateInputsContent(title, subject, content)
{
    if (title != "" && subject != "" && content != "")
        return true;
    return false;
}


//=========================================showing notes========================================== 


function showAllNotes() {


    var notes = {
        name: originalUserId,
        password: originalPassword
    };
    $.ajax({
        url: "EnotesShowingAllNotesServlet", // my addnotes servlet
        type: "POST",
        data: notes,
        success: function (response) {
            if (response == "not")
            {
                Swal.fire({
                    title: "Error!",
                    text: "You don't have any notes !",
                    icon: "error",
                    showConfirmButton: false,
                    timer: 2000 // Display the message for 1 second
                });
            } else {

                var cardFirstPart = `<div class="slide-container swiper">
                                            <div class="slide-content">
                                                 <div class="card-wrapper swiper-wrapper">`
                var cardLastPart = `</div>
                                      </div>
                                   <div class="swiper-button-prev swiper-navBtn my-swiper"></div>
                                 <div class="swiper-button-next swiper-navBtn"></div>
                               <div class="swiper-pagination swiper-my-pagination"> </div>
                            </div>
                         </div>`;
//                    
//                    
                document.getElementById("showNodtsId").innerHTML = cardFirstPart + response + cardLastPart;
                document.getElementById("showNodtsId").style.display = "block";
                document.getElementById("main-body").style.pointerEvents = "none";
                swiper = new Swiper(".slide-content", {
                    slidesPerView: 1,
                    spaceBetween: 30,
                    slidesPerGroup: 1,
                    loop: true,
                    loopFillGroupWithBlank: true,
                    pagination: {
                        el: ".swiper-pagination",
                        clickable: true,
                    },
                    navigation: {
                        nextEl: ".swiper-button-next",
                        prevEl: ".swiper-button-prev",
                    },
                });
            }
        },
        error: function (xhr, status, error) {
            // Handle errors of the servlet response
            alert("Request failed" + xhr);
//                        console.log(error);
        },
    });
}


function closeAllNotesFun() {

    document.getElementById("showNodtsId").innerHTML = "";
    document.getElementById("showNodtsId").style.display = "none";
    document.getElementById("main-body").style.pointerEvents = "all";
}


var oldtitle = "";
var oldsubject = "";
var oldcontent = "";



function editFun() {
    var titles = document.getElementsByClassName("title-input");
    var subjects = document.getElementsByClassName("subject-input");
    var contents = document.getElementsByClassName("content-input");
    for (var i = 0; i < contents.length; i++) {
        titles[i].disabled = false;
        subjects[i].disabled = false;
        contents[i].disabled = false;
    }

    var activeCard = event.target.closest(".card");
    oldtitle = activeCard.querySelector(".title-input").value;
    oldsubject = activeCard.querySelector(".subject-input").value;
    oldcontent = activeCard.querySelector(".content-input").value;


    var saveDiv = `<button class="button div_btn1" onclick="saveFun()">Save</button>`;
    var cancelDiv = `<button class="button div-btn2 div-btn3" onclick="cancelFun()">Cancel</button>`;


    var div_btns = document.getElementsByClassName("div_btn");

    for (var i = 0; i < div_btns.length; i++) {

        div_btns[i].innerHTML = saveDiv + cancelDiv;
    }
    swiper.allowSlideNext = false;  // Disable sliding to the next slide
    swiper.allowSlidePrev = false;


}

function validateEditedValue(newtitle, newsubject, newcontent) {
    var error = "Invalid ";
    if (newtitle === "" || newsubject === "" || newcontent === "")
        error = "Please full all the fiedls!!";
    else if (newtitle === oldtitle || newsubject === oldsubject || newcontent === oldcontent)
        error = "Please make change one of them!!";
    if (error === "Invalid")
        return true;
    return error;
}


function saveFun() {
    var activeCard = event.target.closest(".card");
    var newtitle = activeCard.querySelector(".title-input").value;
    var newsubject = activeCard.querySelector(".subject-input").value;
    var newcontent = activeCard.querySelector(".content-input").value;
    if (validateEditedValue(newtitle, newsubject, newcontent)) {

        var notes = {
            name: originalUserId,
            oldtitle: oldtitle,
            newtitle: newtitle,
            oldsubject: oldsubject,
            newsubject: newsubject,
            oldcontent: oldcontent,
            newcontent: newcontent,
            password: originalPassword
        };
        $.ajax({
            url: "EnotesUpdateDataServlet", // my addnotes servlet
            type: "POST",
            data: notes,
            success: function (response) {
                if (response == "success")
                {
                    showAllNotes();
                    Swal.fire({
                        title: "success",
                        text: "Notes updated successfully!!",
                        icon: "success",
                        showConfirmButton: false,
                        timer: 2000 // Display the message for 1 second
                    });
                } else {
                    showAllNotes();
                    Swal.fire({
                        title: "Sorry!",
                        text: "Due to server issues note  is not updated! ",
                        icon: "error",
                        showConfirmButton: false,
                        timer: 2000 // Display the message for 1 second
                    });
                }
            },
            error: function (xhr, status, error) {
                // Handle errors of the servlet response
                console.log("Request failed");
                console.log(error);
            },
        });
    }


}

function cancelFun() {
    closeAllNotesFun();
    showAllNotes();
}
//=======================================delete notes started=====================
function deleteFun() {

    var activeCard = event.target.closest(".card");
    var title = activeCard.querySelector(".title-input").value;
    var subject = activeCard.querySelector(".subject-input").value;
    var content = activeCard.querySelector(".content-input").value;
    document.getElementById("showNodtsId").style.display = "none";
    document.getElementById("main-body").style.pointerEvents = "all";


    swal.fire({
        title: "Confirmation",
        text: "Are you sure you want to delete?",
        icon: "warning",
        showCancelButton: true,
        confirmButtonText: 'OK',
        cancelButtonText: 'Cancel'
    }).then(function (confirmation) {

        if (confirmation.isConfirmed) {
            var notes = {
                name: originalUserId,
                title: title,
                subject: subject,
                content: content,
                password: originalPassword
            };

            $.ajax({
                url: "EnotesDeleteDataServlet", // my addnotes servlet
                type: "POST",
                data: notes,
                success: function (response) {

                    if (response === "success")
                    {
                        Swal.fire({
                            title: "success",
                            text: "Notes deleted successfully!!",
                            icon: "success",
                            showConfirmButton: false,
                            timer: 2000 // Display the message for 1 second
                        }).then(() => {
                            closeAllNotesFun();
                            showAllNotes();
                        });
                    } else {
                        Swal.fire({
                            title: "Sorry!",
                            text: "Due to server issues note  is not deleted! ",
                            icon: "error",
                            showConfirmButton: false,
                            timer: 2000 // Display the message for 1 second
                        }).then(() => {
                            closeAllNotesFun();
                            showAllNotes();
                        });
                    }
                },
                error: function (xhr, status, error) {
                    // Handle errors of the servlet response
                    console.log("Request failed");
                    console.log(error);
                },
            });


        } else {
            Swal.fire({
                title: "success!",
                text: "Deletion cancel notes is not deleted! ",
                icon: "success",
                showConfirmButton: false,
                timer: 2000 // Display the message for 1 second
            }).then(() => {
                closeAllNotesFun();
                showAllNotes();
            });
        }

    });
}
//==================================================delete notes ended===================