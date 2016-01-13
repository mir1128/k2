var React = require('react');
var ReactDOM = require('react-dom');


var Form = React.createClass({
    getInitialState: function () {
        return {name: '', available: false, registered: false};
    },
    handleNameChange: function (e) {
        if (e.target.value == '') return;
        $.ajax({
            url: '/k2/api/register/check/' + e.target.value,
            dataType: 'json',
            cache: false,
            success: function (data) {
                this.setState({name: e.target.value});
                this.setState({available: data.result});
                console.log(this.state)
            }.bind(this),
            error: function (xhr, status, err) {
                //
            }.bind(this)
        });
    },
    handleSubmit: function (e) {
        e.preventDefault();
        var name = this.state.name.trim();
        if (!name) {
            return;
        }
        $.ajax({
            type: 'POST',
            url: '/k2/api/register/' + name,
            dataType: 'json',
            cache: false,
            success: function (data) {
                this.setState({registered: true});
                var statusMatrix = data.status.split(',').map(function (e) {
                    return e.trim();
                });
                this.props.onRegistered({name: data.name, status: statusMatrix});
            }.bind(this),
            error: function (xhr, status, err) {
                //
            }.bind(this)
        });

    },
    render: function () {
        return (
            <form className="form-inline" role="form" onSubmit={this.handleSubmit}>
                <div className="form-group">
                    <label>Register</label>
                    <input className="form-control" type="text" onChange={this.handleNameChange}
                           disabled={this.state.registered}/>
                </div>

                <button type="submit" className="btn btn-default"
                        disabled={this.state.registered || !this.state.available}>
                    {!this.state.available && this.state.name !== '' ? 'Occupied' : 'Submit'}
                </button>
            </form>
        );
    }
});

var Grid = React.createClass({
    render: function () {
        var matrix = this.props.status;
        return (
            <div className="row">
                <div className="col-md-12">
                    <div className="grid-container">
                        {
                            [0, 1, 2, 3].map(function (i) {
                                return <div className="grid-row">
                                    {
                                        [0, 1, 2, 3].map(function (j) {
                                            return <div className="grid-cell">{matrix[i * 4 + j]}</div>;
                                        })
                                    }
                                </div>
                            })
                        }
                    </div>
                </div>
            </div>
        );
    }
});


var GameColumn = React.createClass({
    componentDidMount: function () {
        window.addEventListener("keydown", this.handleKeys);
    },
    handleKeys: function (event) {
        keyMap = {37: 'left', 38: 'up', 39: 'right', 40: 'down'};
        if (typeof keyMap[event.keyCode] === 'undefined') return;
        $.ajax({
            type: 'POST',
            url: '/k2/api/move/' + this.state.name + '/' + keyMap[event.keyCode] ,
            dataType: 'json',
            cache: false,
            success: function (data) {
                this.setState({registered: true});
                var statusMatrix = data.status.split(',').map(function (e) {
                    return e.trim();
                });
                this.setState({status: statusMatrix});
            }.bind(this),
            error: function (xhr, status, err) {
                //
            }.bind(this)
        });
    },
    getInitialState: function () {
        return {name: '二七', status: [2048, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]};
    },
    handleRegister: function (nameAndStatus) {
        this.setState(nameAndStatus);
    },
    render: function () {
        return (
            <div className="col-md-6" tabIndex="1">
                <Form onRegistered={this.handleRegister}/>
                <Grid name={this.state.name} status={this.state.status}/>
            </div>
        );
    }
});


var Rank = React.createClass({
    loadCommentsFromServer: function () {
        $.ajax({
            url: '/k2/api/scores?offset=0&size=10',
            dataType: 'json',
            cache: false,
            success: function (data) {
                // var convertedData = data.map(function (entry) {
                //         return {'name': Object.keys(entry)[0], 'score': entry[Object.keys(entry)[0]]};
                //     })
                //     .sort(function (a, b) {
                //         return b.score - a.score;
                //     });
                this.setState({data: data});
            }.bind(this),
            error: function (xhr, status, err) {
            }.bind(this)
        });
    },
    getInitialState: function () {
        return {data: []};
    },
    componentDidMount: function () {
        this.loadCommentsFromServer();
        setInterval(this.loadCommentsFromServer, 1000);
    },
    render: function () {
        return (
            <div className="list-group">
                <a href="#" className="list-group-item active">Rank</a>
                {this.state.data.map(function (entry) {
                    return <div key={entry.name} className="list-group-item"><b>{entry.name}</b> - {entry.score}</div>;
                })}
            </div>
        );
    }
});


var RankColumn = React.createClass({
    render: function () {
        return (
            <div className="col-md-6">
                <Rank />
            </div>
        );
    }
});


var Row = React.createClass({
    render: function () {
        return (
            <div className="row">
                <GameColumn />
                <RankColumn />
            </div>
        );
    }
});

ReactDOM.render(
    <Row />,
    document.getElementById('container')
);