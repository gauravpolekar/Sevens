import './App.css';
import React, { Component } from 'react';

class App extends Component {
  state = {
    cards: []
  };

  async componentDidMount() {
    const response = await fetch('http://localhost:8080/seven/deck');
    try {
        const body = await response.json();
        console.log(body);
        this.setState({cards: body.cards});
    } catch(error) {
        console.error(error)
    }
  }

  render() {
    const {cards} = this.state;
    return (
        <div className="App">
          <header className="App-header">
            <div className="App-intro">
              <h2>Cards</h2>
              {cards.map(card =>
                  <div class="playing-card">
                    {card.unicode}
                  </div>
              )}

            </div>
          </header>
        </div>
    );
  }
}
export default App;