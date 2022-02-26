import logo from './logo.svg';
import './App.css';

class App extends Component {
  state = {
    cards: []
  };

  async componentDidMount() {
    const response = await fetch('/');
    const body = await response.json();
    this.setState({cards: body});
  }

  render() {
    const {cards} = this.state;
    return (
        <div className="App">
          <header className="App-header">
            <img src={logo} className="App-logo" alt="logo" />
            <div className="App-intro">
              <h2>Clients</h2>
              {clients.map(client =>
                  <div key={client.id}>
                    {client.name} ({client.email})
                  </div>
              )}
            </div>
          </header>
        </div>
    );
  }
}
export default App;