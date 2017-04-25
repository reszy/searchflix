/**
 * Created by Rafał on 24.04.2017.
 */
var SearchBar = React.createClass({
    handleSubmit: function (e) {
        e.preventDefault();
        var search = this.refs.search.getDOMNode().value.trim();
        if (!search) {
            return;
        }
        this.props.onSearchSubmit({search: search});
        this.refs.search.getDOMNode().value = '';
    },
    render: function () {
        return (
            <form className="searchBar" onSubmit={this.handleSubmit}>
                <input type="text" placeholder="Search" ref="search" />
                <input type="submit" value="Sreach" />
            </form>
        );
    }
});

var MovieList = React.createClass({
    render: function () {
        var movieNodes = this.props.data.map(function (movie, index) {
            return (
                <div className="movie">
                    <h2 key={index}>{movie.title}</h2>
                    <img src={movie.picture} />
                </div>
            );
        });
        return (
            <div className="movieList">
                {movieNodes}
            </div>
        );
    }
});

var SearchBox = React.createClass({
    handleSearchSubmit: function (search) {
        var comments = this.state.data;
        query.push(search);
        this.setState({data: query}, function () {
            $.ajax({
                url: this.props.url,
                dataType: 'json',
                type: 'POST',
                data: movie,
                success: function (data) {
                    this.setState({data: data});
                }.bind(this),
                error: function (xhr, status, err) {
                    console.error(this.props.url, status, err.toString());
                }.bind(this)
            });
        });
    },
    loadResultsFromServer: function () {
        $.ajax({
            url: this.props.url,
            dataType: 'json',
            success: function (data) {
                this.setState({data: data});
            }.bind(this),
            error: function (xhr, status, err) {
                console.error(this.props.url, status, err.toString());
            }.bind(this)
        });
    },
    getInitialState: function () {
        return {data: this.props.data};
    },
    componentDidMount: function () {
        this.loadResultsFromServer();
        setInterval(this.loadResultsFromServer, this.props.pollInterval);
    },
    render: function () {
        return (
            <div className="movieBox">
                <h1>SearchFlix</h1>
                <SearchBar onCommentSubmit={this.handleCommentSubmit} />
                <MovieList data={this.state.data} />
            </div>
        );
    }
});

var renderClient = function (movies) {
    var data = movies || [];
    React.render(
        <SearchBox data={data} url='results.json'/>,
        document.getElementById("content")
    );
};

var renderServer = function (movies) {
    var data = Java.from(movies);
    return React.renderToString(
        <SearchBox data={data} url='results.json'/>
    );
};