

var gameBoardSize = 40;
var gamePoints = 0;
var gameSpeed = 100;

$(document).ready(function () {
    createBoard();
    $(".btn").click(function() {
        startGame();
    });
});

var Snake = {
    position: [[20, 20], [20, 19], [20, 18], [20, 17]], // snake start position
    size: 4,
    direction: "r",
    alive: true
}

var Food = {
    position: [],
    present: false
}

function createBoard() {
    $("#gameBoard").empty();
    var size = gameBoardSize;

    for (i = 0; i < size; i++) {
        $("#gameBoard").append('<div class="row"></div>');
        for (j = 0; j < size; j++) {
            $(".row:last-child").append('<div class="pixel"></div>');
        }
    }
    $(".row:nth-child(" + 20 + ") > .pixel:nth-child(" + 20 + ")").addClass("foodPixel");


}

function moveSnake() {
    var head = Snake.position[0].slice();

    switch (Snake.direction) {
        case 'r':
            head[1] += 1;
            break;
        case 'l':
            head[1] -= 1;
            break;
        case 'u':
            head[0] -= 1;
            break;
        case 'd':
            head[0] += 1;
            break;
    }

    // check after head is moved
    if (alive(head)) {
        // draw head
        $(".row:nth-child(" + head[0] + ") > .pixel:nth-child(" + head[1] + ")").addClass("snakePixel");

        // draw rest of body loop
        for (var i = 0; i < Snake.size; i++) {
            $(".row:nth-child(" + Snake.position[i][0] + ") > .pixel:nth-child(" + Snake.position[i][1] + ")").addClass("snakePixel");
        }

        // if head touches food
        if (head.every(function(e,i) {
                return e !== Food.position[i];
            })) {
            Snake.size++;
            Food.present = false;
            $(".row:nth-child(" + Food.position[0] + ") > .pixel:nth-child(" + Food.position[1] + ")").removeClass("foodPixel");


        } else {
            $(".row:nth-child(" + Snake.position[Snake.size-1][0] + ") > .pixel:nth-child(" + Snake.position[Snake.size-1][1] + ")").removeClass("snakePixel");
            Snake.position.pop();
        }
        Snake.position.unshift(head);
    } else {
        gameOver();
    }
}



function saveOnKeyPress() {
    $(document).one("keydown", function(key) {
        switch(key.which) {
            case 37: // left arrow
                if (Snake.direction != "r") {Snake.direction = "l";}
                break;
            case 38: // up arrow
                if (Snake.direction != "d") {Snake.direction = "u";}
                break;
            case 39: // right arrow
                if (Snake.direction != "l") {Snake.direction = "r";}
                break;
            case 40: // down arrow
                if (Snake.direction != "u") {Snake.direction = "d";}
                break;
        }
    });
}

function gameLoop() {
    setTimeout(function() {
        saveOnKeyPress();
        moveSnake();
        if (Snake.alive) {gameLoop(); }
    }, gameSpeed);
}

function alive(head) {
    // head check
    if (head[0] < 1 || head[0] > 40 || head[1] < 1 || head[1] > 40) {
        return false;
    }
    // wall collision
    if (Snake.position[0][0] < 1 || Snake.position[0][0] > 40 || Snake.position[0][1] < 1 || Snake.position[0][1] > 40) {
        return false;
    }
    // self collision
    for (var i = 1; i < Snake.size; i++) {
        if ((Snake.position[0]).every(function(element,index) {
                return element === Snake.position[i][index];
            })) {
            return false;
        }
    }
    return true;
}

function gameOver() {
    Snake.alive = false;
    console.log("Game Over!");
    $(".overlay").show();

    $.ajax({
        url: "/snake",
        type: "PUT",
        success: function (res) {
            console.log("success: " + res);
            $("#gameOver").text("YOU LOSE ! \n "+ res + " seconds");
        }
    });


    $("#gameOver").show();
    var blink = function() {
        $(".row:nth-child(" + Snake.position[0][0] + ") > .pixel:nth-child(" + Snake.position[0][1] + ")").toggleClass("snakePixel");
    }

    var i = 0;
    function blinkLoop() {
        blink();
        setTimeout(function() {
            i++;
            if (i < 10) { blinkLoop();}
        }, 400);
    }
    blinkLoop();
}

function startGame() {
    // reset game settings
    Snake.size = 4;
    Snake.position = [[20,20],[20,19],[20,18],[20,17]];
    Snake.direction = "r";
    Snake.alive = true;
    gameSpeed = 100;
    gamePoints = 0;
    Food.present = false;

    // start game
    createBoard();
    $(".overlay").hide();
    gameLoop();
}
