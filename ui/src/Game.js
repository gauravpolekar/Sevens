import React, { Component } from 'react';
import Player from './Player';
import CardOnTable from './CardOnTable';

class Game extends Component {
  state = {
    cards: []
  };

  constructor(props) {
    super(props);
    //this.updateCards = this.updateCards.bind(this);
  }
  
  
  async componentDidMount() {
    // const response = await fetch('http://localhost:8080/seven/deck');
    // try {
    //     const body = await response.json();
    //     console.log(body);
    //     this.setState({cards: body.cards});
    // } catch(error) {
    //     console.error(error)
    // }
  }

  startGame = () =>{
    fetch('http://localhost:8080/seven/start/' + localStorage.getItem('gameId'))
    .then(() => this.updateCards()
      );
  }

  updateCards = () => {
    fetch('http://localhost:8080/seven/' + localStorage.getItem('gameId')
    + "/" + localStorage.getItem('player') + "/cards"
  ).then(response => response.json().then((gameBody) =>{
    console.log(gameBody);
    this.setState({cards : gameBody})
  })) 
  }


  render() {
    const { cards } = this.state;
    return (
      <div className="App">
        <h2>Cards<button onClick={this.startGame}>Start</button></h2>
        <Player />
        <CardOnTable/>
        {cards.map(card =>
          <div className={"playing-card " + card.suit}>
            {card.unicode}
          </div>
        )}
      </div>
    );
  }
}
export default Game;