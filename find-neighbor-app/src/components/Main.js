import React, {Component} from "react";
import Button from "@material-ui/core/Button";
import MainPage from './MainPage/MainPage';

class Main extends Component {
    constructor(props) {
        super(props);
    }

    // render() {
    //     let findNeighborButton;
    //     if (this.props.currentUser) {
    //         findNeighborButton =
    //             <Button className="findNeighborButton" onClick={() => {
    //                 this.props.history.replace('/findNeighbor')
    //             }}>
    //                 Find Neighbor
    //             </Button>
    //
    //     }
    //     ;
    //
    //     return (
    //         <p>It is main page</p>,
    //             <br></br>,
    //             <p>Here you can set your preferences</p>,
    //             <div>
    //                 {findNeighborButton}
    //             </div>
    //
    //     );
    // }

    render() {
        return (
            <MainPage />
        )
    }
}

export default Main