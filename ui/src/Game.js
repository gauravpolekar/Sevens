import React, { Component } from 'react';
import Player from './Player';
import CardOnTable from './CardOnTable';

class Game extends Component {
  state = {
    cards: [],
    cardsOnTable: {},
    errorMessage: ""
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
    try {
      fetch(url, {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(p)
      }).then((res) => {
        if (!res.ok) {
           res.text().then(text => { this.setState({ errorMessage: text })})
        }
      });
    } catch (error) {
      console.log("****************");
      this.setState({ errorMessage: error });
    }

  }
  render() {
    const { cards } = this.state;
    const error = () => {
      if (this.state.errorMessage != "") {
        return(
        <div className="alert alert-dark" roleName="alert">
          {this.state.error}
        </div>
        )
      }
      return ("");
    }
    return (
      <div class="container">
        <div class="row">
          <div className='col-sm'>
        <button onClick={this.startGame} className="btn-primary col-sm" >Start</button>
        {error}
        </div>
        <div class="row">
        <Player />
        </div>
        <div class="row">
        <hr/>
        </div>
        <div class="row">
        <CardOnTable/>
        </div>
        <div class="row">
        <hr/>
        </div>
        <div class="d-flex">
        {cards.map(card =>
          <div className={"playing-card " + card.suit} onClick={() => this.play(card)}>
            {card.unicode}
          </div>
        )}
        </div>
      </div>
      </div>
    );
  }
}
export default Game;