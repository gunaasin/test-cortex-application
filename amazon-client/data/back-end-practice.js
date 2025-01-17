const xhr = new XMLHttpRequest();
// console.log(xhr);

xhr.addEventListener('load',()=>{
    console.log(xhr.response);
});

xhr.open('GET',"https://supersimplebackend.dev");
xhr.send();