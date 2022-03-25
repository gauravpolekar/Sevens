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
    return (
        <div>
          <h2 className='startGame'>Badam Sat / 7 of ❤️</h2>
          
          <div className="startGameBox">
          <form onSubmit={this.addPlayer}>
          <div className="row">
            <div className='welcomeBoard'>Welcome! Start a New Game </div>
            <div>
            <div style={{float:"left"}}>
              <input className="form-control" name="playerName" placeholder='Player name'/>
              </div>
              <div style={{float:"left"}}>
            <button type='submit' className='playButton'>Play</button>
            </div>
            </div>
            <div>
              <br/>
              <br/>
            <div className='shareLink'>Share this link with your friends to play this game.</div>
            <input className='copyUrl' value={window.location.href}/>
            </div>
            </div>
            
          </form>
          </div>
          </div>
          
        
      );
  }
}
export default Home;