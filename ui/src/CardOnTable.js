import React, { Component } from 'react';

class CardOnTable extends Component {

  constructor(props) {
    super(props);
    this.state = {
      cards: {}
    };
  }

  async componentDidMount() {
    this.interval = setInterval(() => this.getCardsOnTable(), 5000);
  }

  async getCardsOnTable() {
    console.log(localStorage.getItem('gameId'));
    const response = await fetch('http://localhost:8080/seven/table/' + localStorage.getItem('gameId'));
    try {
      const body = await response.json();
      console.log(body);
      this.setState({ cards: body });
      
    } catch (error) {
      console.error(error)
    }
  }

  componentWillUnmount() {
    clearInterval(this.interval);
  }
  render() {
    if (this.state.cards === undefined || this.state.cards === 0) {
      return (<>🂠</>);
    }
    return (
      <div className="container">
        <div>
          {
            Object.keys(this.state.cards).map((card) => {
              return (
                <div className='tableRow'> 
                  {this.state.cards[card].map(c =>
                  <div className={"playing-card " + card} key={c.face}>
                    {c.unicode}
                  </div>
                )
                
                }<hr/>
                </div>
              );
            }
            )
          }
      </div>
      </div>
    );
  }
}
export default CardOnTable;