var posX=0;
var posY=0;

createMap();

async function createMap() {
    refreshRover();

    //Obtener la info de los obstaculos
    let obsacleResponse = await fetch('/api/obstacle', {
        method:'GET',
        headers:{
            'Content-type':'application/json'
        }
    });
    let obstaclesJson = await obsacleResponse.json();

    //Los creamos
    obstaclesJson.forEach(obstacleJson => {
        createRock(obstacleJson.x, obstacleJson.y);
    }); 
    
}

async function refreshRover(){
     //Obtener la info del rover
     let roverResponse = await fetch('/api/rover', {
        method:'GET',
        headers:{
            'Content-type':'application/json'
        }
    });
    let roverJson = await roverResponse.json();

    //Movemos al rover
    moveRover(roverJson.x,roverJson.y);

}

function moveRover(x, y) {
    document.getElementById("rover").style.left = (x * 100) + "px";
    document.getElementById("rover").style.top = (y * 100) + "px";
}


function createRock(x, y) {
    var rock = document.createElement("img");
    rock.setAttribute("src", "img/rock.png");
    rock.setAttribute("class", "rock");

    var container = document.querySelector(".container");
    container.appendChild(rock);

    rock.style.left = (x * 100) + "px";
    rock.style.top = (y * 100) + "px";
}

function playMoveSound(){
    var audioElement = document.createElement("audio");
    audioElement.src="sounds/mars.mp3";
    
    audioElement.controls=false;
    audioElement.autoplay=true;

    document.getElementById("container").appendChild(audioElement);
}  

function clickRotateRight() {
    
    sendCommand("R")

    // posY++;
   // moveRover(posX, posY);
    playMoveSound()
    //alert("Right");
}

function clickRotateLeft() {
    
    sendCommand("L")

    //posY--;
    //moveRover(posX, posY);
    playMoveSound()
   // alert("Left");
}

async function clickFoward() {
    sendCommand("F")

   //posX++;
   //moveRover(posX, posY);
   playMoveSound()
   // alert("Foward");
}

function clickBack() {
    sendCommand("B")
    //posX--;
   // moveRover(posX, posY);
    playMoveSound()
    //alert("Back");
}

async function sendCommand(command){
    let requestBody = {
        "commands":[command]
    }

    await fetch('/api/rover/commands', {
        method:'POST',
        headers:{
            'Content-type':'application/json'
        }, 
        body : JSON.stringify(requestBody)
    });
    await refreshRover();
}

 