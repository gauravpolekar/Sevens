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
      const game = await (await fetch(`https://sevenofheart.herokuapp.com/seven/createGame`)).text();
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
    let p = {};
    try {
    p = { name: e.target.playerName.value };
    } catch(error) {
      p = { name: localStorage.getItem('player')};
    }
    this.setState(p, () => {
      let url = `https://sevenofheart.herokuapp.com/seven/player/add/` + this.state.gameId;

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
    try {
    localStorage.setItem('player', e.target.playerName.value);
    } catch(error) {
      //do nothing
    }
  });
}


  render() {
    if (Object.keys(this.state.player).length === 0 || this.state.player.name == null || this.state.player.name == 'null') {
      return (
        <div class="container">
          <h4>Start Game</h4>
          
          
          <form onSubmit={this.addPlayer}>
          <div class="row">
          <div class="col-sm">
            <label className="form-label">Player name :</label> 
            </div>
            <div class="col-sm">
              <input className="form-control" name="playerName" />
              </div>
              <div class="col-sm">
            <button type='submit' className="btn-primary col-sm">Play</button>
            </div>
            </div>
          </form>
          
          </div>
          
        
      );
    } else {
      return (
        <div className="App">
        
          <p>Player name: {this.state.player.name} </p>
          <form onSubmit={this.addPlayer}>
            <button type='submit'>Play</button>
          </form>
        </div>
      );
    }
  }
}
export default Home;