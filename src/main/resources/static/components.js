"use strict";

/**
 * Created by Rafał on 24.04.2017.
 */
var SearchBar = React.createClass({
    displayName: "SearchBar",

    handleSubmit: function handleSubmit(e) {
        e.preventDefault();
        var search = this.refs.search.getDOMNode().value.trim();
        if (!search) {
            return;
        }
        this.props.onSearchSubmit({ search: search });
        this.refs.search.getDOMNode().value = '';
    },
    render: function render() {
        return React.createElement(
            "form",
            { className: "searchBar", onSubmit: this.handleSubmit },
            React.createElement("input", { type: "text", placeholder: "Search", ref: "search" }),
            React.createElement("input", { type: "submit", value: "Search" })
        );
    }
});

var MovieList = React.createClass({
    displayName: "MovieList",

    render: function render() {
        var movieNodes = this.props.data.map(function (movie, index) {
            return React.createElement(
                "div",
                { className: "movie" },
                React.createElement(
                    "h2",
                    { key: index },
                    movie.title
                ),
                React.createElement("img", { src: movie.picture })
            );
        });
        return React.createElement(
            "div",
            { className: "movieList" },
            movieNodes
        );
    }
});

var SearchBox = React.createClass({
    displayName: "SearchBox",

    handleSearchSubmit: function handleSearchSubmit(search) {
        var comments = this.state.data;
        query.push(search);
        this.setState({ data: query }, function () {
            $.ajax({
                url: this.props.url,
                dataType: 'json',
                type: 'POST',
                data: movie,
                success: function (data) {
                    this.setState({ data: data });
                }.bind(this),
                error: function (xhr, status, err) {
                    console.error(this.props.url, status, err.toString());
                }.bind(this)
            });
        });
    },
    loadResultsFromServer: function loadResultsFromServer() {
        $.ajax({
            url: this.props.url,
            dataType: 'json',
            success: function (data) {
                this.setState({ data: data });
            }.bind(this),
            error: function (xhr, status, err) {
                console.error(this.props.url, status, err.toString());
            }.bind(this)
        });
    },
    getInitialState: function getInitialState() {
        return { data: this.props.data };
    },
    componentDidMount: function componentDidMount() {
        this.loadResultsFromServer();
        setInterval(this.loadResultsFromServer, this.props.pollInterval);
    },
    render: function render() {
        return React.createElement(
            "div",
            { className: "movieBox" },
            React.createElement(
                "h1",
                null,
                "Search flix"
            ),
            React.createElement(SearchBar, { onCommentSubmit: this.handleCommentSubmit }),
            React.createElement(MovieList, { data: this.state.data })
        );
    }
});

var renderClient = function renderClient(movies) {
    var data = movies || [];
    React.render(React.createElement(SearchBox, { data: data, url: "results.json" }), document.getElementById("content"));
};

var renderServer = function renderServer(movies) {
    var data = Java.from(movies);
    return React.renderToString(React.createElement(SearchBox, { data: data, url: "results.json" }));
};