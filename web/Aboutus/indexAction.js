document.getElementById("about_app").onclick = function(){
  console.log("click about app button");
  document.getElementById("first").style.display = "none";
  document.getElementById("second").style.display = "block";
};
document.getElementById("about_us").onclick = function(){
  console.log("click about us button");
  document.getElementById("first").style.display = "none";
  document.getElementById("third").style.display = "block";
};
