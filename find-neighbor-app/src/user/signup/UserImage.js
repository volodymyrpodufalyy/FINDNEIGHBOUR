import React, {Component} from "react";
import {userImageRequest} from "../../util/APIUtils";
import {LogoImage} from "../../components/userPictureImage.png"

class UserImage extends Component{
    constructor(props) {
        super(props);
        this.state = {
            images: [],
            imageUrls: [],
            message: ''
        }
    }

    selectImages = (event) => {
        let images = [];
        for (let i = 0; i < event.target.files.length; i++) {
            images[i] = event.target.files.item(i);
        }
        images = images.filter(image => image.name.match(/\.(jpg|jpeg|png|gif)$/));
        let message = `${images.length} valid image(s) selected`;
        this.setState({ images, message })
        console.log(images)
    };
    uploadImages = () => {
        const uploaders = this.state.images.map(image => {
            const data = new FormData();
            data.append("image", image, image.name);
            const userImage = {}
            ;

            console.log(this.state.images);
            return userImageRequest(LogoImage, this.props.match.param)
                .then(response => {

                })
        });
// // Once all the files are uploaded
//         userImageRequest.all(uploaders).then(() => {
//             console.log('done');
//         }).catch(err => alert(err.message));
     };
    render() {
        return (
            <div>
                <br/>
                <div className="col-sm-12">
                    <h1>Image Uploader</h1><hr/>
                    <div className="col-sm-4">
                        <input className="form-control " type="file"
                               onChange={this.selectImages} multiple/>
                    </div>
                    <p className="text-info">{this.state.message}</p>
                    <br/><br/><br/>
                    <div className="col-sm-4">
                        <button className="btn btn-primary" value="Submit"
                                onClick={this.uploadImages}>Submit</button>
                    </div>
                </div>
                <br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><br/><hr/><br/>
                <div className="row col-lg-12">
                    {
                        this.state.imageUrls.map((url, i) => (
                            <div className="col-lg-2" key={i}>
                                <img src={this.folder} className="img-rounded img-responsive"
                                     alt="not available"/><br/>
                            </div>
                        ))
                    }
                </div>

            </div>

        );
    }
}
export default UserImage