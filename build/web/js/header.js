/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function myDropDown() {
    
    var btnMenu = document.getElementsByClassName("nav-link");
    if (btnMenu[1].classList[1] !== "show") {
        btnMenu[1].style.display = "block";
        btnMenu[1].classList.add("show");
    } else {
        btnMenu[1].style.display = "none";
        btnMenu[1].classList.remove("show");
    }

}