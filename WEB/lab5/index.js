function allowDrop(ev) {
    ev.preventDefault();
}

function drag(ev) {
    ev.dataTransfer.setData("dropHere", ev.target.id);
}

function drop(ev) {
    ev.preventDefault();
    var data = ev.dataTransfer.getData("dropHere");
    ev.target.appendChild(document.getElementById(data));
    check();
}
function check(){
  //console.log(document.getElementById("td1").firstChild.getAttribute("id"));
   if(document.getElementById("td1").firstChild.nextElementSibling.id=="img1" &&
   document.getElementById("td2").firstChild.nextElementSibling.id=="img2" &&
   document.getElementById("td3").firstChild.nextElementSibling.id=="img3" &&
   document.getElementById("td4").firstChild.nextElementSibling.id=="img4" &&
   document.getElementById("td5").firstChild.nextElementSibling.id=="img5" &&
   document.getElementById("td6").firstChild.nextElementSibling.id=="img6" &&
   document.getElementById("td8").firstChild.nextElementSibling.id=="img8" &&
   document.getElementById("td9").firstChild.nextElementSibling.id=="img9"){
     alert("WELL DONE!");
   }


}
