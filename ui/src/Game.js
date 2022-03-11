import React, { Component } from 'react';
import Player from './Player';
import CardOnTable from './CardOnTable';

class Game extends Component {
  state = {
    cards: [],
    cardsOnTable: {}
  };

  constructor(props) {
    super(props);
  }
  
  
  async componentDidMount() {
    this.interval = setInterval(() => this.updateCards(), 5000);
  }

  startGame = () =>{
    fetch('http://localhost:8080/seven/start/' + localStorage.getItem('gameId'))
    .then(() => this.updateCards()
      );
  }
  componentWillUnmount() {
    clearInterval(this.interval);
  }
  updateCards = () => {
    fetch('http://localhost:8080/seven/' + localStorage.getItem('gameId')
    + "/" + localStorage.getItem('player') + "/cards"
  ).then(response => response.json().then((gameBody) =>{
    console.log(gameBody);
    this.setState({cards : gameBody})
  })) 
  }


  play = (cardToPlay)=> {
    let player = localStorage.getItem('player');
    let gameId = localStorage.getItem('gameId');
    console.log("Playing ", cardToPlay);
    let p ={ player : {name : player},
            card : cardToPlay
            };
    let url =`http://localhost:8080/seven/playTurn/` + gameId;
    fetch(url, {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      },
      body: JSON.stringify(p)
    });

  }
  render() {
    const { cards } = this.state;
    return (
      <div className="App">
        <h2>Cards<button onClick={this.startGame}>Start</button></h2>
        <Player />
        <hr/>
        <br/>
        <CardOnTable/>
        <hr/>
        <br/>
        {cards.map(card =>
          <div className={"playing-card " + card.suit} onClick={() => this.play(card)}>
            {card.unicode}
          </div>
        )}
      </div>
    );
  }
}
export default Game;