var db=firebase.firestore();
const list_div=document.querySelector("#list_div");
var val=document.getElementById("d").Value;

document.write("");
document.write("");
document.write("");
db.collection(val).get().then((querySnapshot)=>{
    querySnapshot.forEach((doc)=>{document.write("<br></br>");
    list_div.innerHTML+="<div class='list-item'><h2>"+document.write(doc.data().name)+"<h2><h3>"+document.write(doc.data().dob)+"<h3><h4>"+document.write(doc.data().email)+"<h4><h5>"+document.write(doc.data().phone)+"<h5></div>"
});
document.write("<br></br>");
document.write("");
});
//db.collection(val).get().then((querySnapshot)=>{
  //  querySnapshot.forEach((doc)=>{
    //    document.write("<br></br>");
      //  list_div.innerHTML+="<div class='list-item'>"
    //})})