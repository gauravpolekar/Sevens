import React, { Component } from 'react';

class Home extends Component {

  constructor(props) {
    super(props);
    this.state = {
      gameId: '',
      player: {}
    };
  }
  
  async componentDidMount() {
    let id = this.props.match.params.id;
    if (id === undefined) {
      const game = await (await fetch(`http://localhost:8080/seven/createGame`)).text();
      console.log(game)
      this.setState({ gameId: game });
      id = game;
    } else {
      this.setState({ gameId: id });
    }
    localStorage.setItem('gameId', id);
    this.setState({ player: { name: localStorage.getItem('player') } });
    console.log(this.props);
    this.props.history.push('/' + id)
  }

  addPlayer = (e) => {
    e.preventDefault();
    let p = { name: e.target.playerName.value };
    this.setState(p, () => {
      let url = `http://localhost:8080/seven/player/add/` + this.state.gameId;

      fetch(url, {
        method: 'POST',
        headers: {
          'Accept': 'application/json',
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(p)
      }).then(() => {
        this.props.history.push('/game/' + this.state.gameId)
      }  
    );

    console.log(this.state);
    localStorage.setItem('player', e.target.playerName.value);
  });
}

  render() {
    if (Object.keys(this.state.player).length === 0 || this.state.player.name == null || this.state.player.name == 'null') {
      return (
        <div className="App">
          <h2>Start Game</h2>
          <form onSubmit={this.addPlayer}>
            <span>Player name :</span> <input name="playerName" />
            <button type='submit'>Play</button>
          </form>
        </div>
      );
    } else {
      return (
        <div className="App"><p>Player name: {this.state.player.name} </p>
          <p>Game Id: {this.state.gameId} </p>
        </div>
      );
    }
  }
}
export default Home;