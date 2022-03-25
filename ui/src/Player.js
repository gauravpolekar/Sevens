import React, { Component } from 'react';

class Player extends Component {

  constructor(props) {
    super(props);
    this.state = {
      players: []
    };
  }

  async componentDidMount() {
    this.interval = setInterval(() => this.getPlayers(), 5000);
  }

  async getPlayers() {
    console.log(localStorage.getItem('gameId'));
    const response = await fetch('https://sevenofheart.herokuapp.com/seven/' + localStorage.getItem('gameId'));
    try {
      const body = await response.json();
      console.log(body);
      this.setState({ players: body.players });
      // if (body.status === "STARTED") {
      //   clearInterval(this.interval);    
      // }
    } catch (error) {
      console.error(error)
    }
  }

  componentWillUnmount() {
    clearInterval(this.interval);
  }
  render() {
    if (this.state.players === undefined || this.state.players.length === 0) {
      return (<></>);
    }
    const activePlayer = {
      backgroundColor : '#b51200',
      color : 'white',
      fontWeight : 'bold',
      width:'100%',
      textAlign:'center'

    }
    const normalPlayer = {
      backgroundColor : 'white',
      color : 'black',
      textAlign:'center'
        }
    return (
      <div>
        <div class="row">
     <div class="card">
          {this.state.players.map(player =>
          <div style={player.currentPlayer ? activePlayer: normalPlayer}   >
            {player.name} 
          </div>
          )}
      </div>
      </div>
      </div>
    );
  }
}
export default Player;