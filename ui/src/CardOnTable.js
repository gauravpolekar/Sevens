import React, { Component } from 'react';

class CardOnTable extends Component {

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
    const response = await fetch('http://localhost:8080/seven/' + localStorage.getItem('gameId'));
    try {
      const body = await response.json();
      console.log(body);
      this.setState({ players: body.players });
      if (body.status == "STARTED") {
        clearInterval(this.interval);    
      }
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
    return (
      <div className="App">
        {this.state.players.map(player =>
          <div class="card">
            {player.name}
          </div>
        )}

      </div>
    );
  }
}
export default CardOnTable;