var app = new Vue({
    el: '#app',
    data: {
        localUrl: 'http://localhost:8082/api/game',
        pit1: 6,
        pit2: 6,
        pit3: 6,
        pit4: 6,
        pit5: 6,
        pit6: 6,
        pit7: 0,
        pit8: 6,
        pit9: 6,
        pit10: 6,
        pit11: 6,
        pit12: 6,
        pit13: 6,
        pit14: 0,
        pit: 4,
        gameId: -1,
        playerId: 1,
        firstPlayerName: "",
        secondPlayerName: "",
        turnMessage: "It is player one's turn",
        message: ""
    },
    watch: {},
    methods: {
        startgame: () => {
            const request_url_startGame = app.localUrl + '/regular-game-start' + '?first-player-name=' + 'ali' + '&second-player-name=' + 'kumru';
            return new Promise((resolve, reject) => {
                axios.get(request_url_startGame, {headers: {'X-Requested-With': 'XMLHttpRequest'}})
                    .then(function (response) {
                        resolve(response);
                        app.gameId = response.data;
                        app.pit1 = 6;
                        app.pit2 = 6;
                        app.pit3 = 6;
                        app.pit4 = 6;
                        app.pit5 = 6;
                        app.pit6 = 6;
                        app.pit7 = 0;
                        app.pit8 = 6;
                        app.pit9 = 6;
                        app.pit10 = 6;
                        app.pit11 = 6;
                        app.pit12 = 6;
                        app.pit13 = 6;
                        app.pit14 = 0;
                        app.playerId = 1;
                        console.log(response.data);
                    })
                    .catch(function (error) {
                        reject(error);
                        app.message = error.response.data.errorMessage;
                    })
            });
        },
        move: (event) => {
            const targetId = event.currentTarget.id;
            const request_url_move = app.localUrl + '/move-regular' + '?game-id=' + app.gameId + '&player=' + app.playerId + "&pit=" + parseInt(targetId);
            return new Promise((resolve, reject) => {
                axios.put(request_url_move, {headers: {'X-Requested-With': 'XMLHttpRequest'}})
                    .then(function (response) {
                        resolve(response);
                        const gameBoard = response.data.gameBoard;
                        app.orderBoard(gameBoard);
                        if (response.data.over) {
                            app.message = "Game is over. The winner is " + response.data.winner;
                        }
                        app.playerId = response.data.turn;
                        app.turnMessage = app.playerId == 1 ? "It is player one's turn" : "It is player two's turn";


                    })
                    .catch(function (error) {
                        reject(error);
                        app.message = error.response.data.errorMessage;
                    })
            });
        },
        orderBoard: (gameBoard) => {
            app.pit1 = gameBoard[1];
            app.pit2 = gameBoard[2];
            app.pit3 = gameBoard[3];
            app.pit4 = gameBoard[4];
            app.pit5 = gameBoard[5];
            app.pit6 = gameBoard[6];
            app.pit7 = gameBoard[7];
            app.pit8 = gameBoard[8];
            app.pit9 = gameBoard[9];
            app.pit10 = gameBoard[10];
            app.pit11 = gameBoard[11];
            app.pit12 = gameBoard[12];
            app.pit13 = gameBoard[13];
            app.pit14 = gameBoard[14];
        }
    }
})