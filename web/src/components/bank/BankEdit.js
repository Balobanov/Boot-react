import React, {PureComponent, Component, PropTypes} from "react";
import {reduxForm} from "redux-form";
import {banksEdit, banksUpdate} from "../../actions/BankActions";
import {connect} from "react-redux";

const validateFormFields = ['name'];
const formName = 'bank';


@connect(
    state => ({}), {
        banksEdit,
        banksUpdate
    })
@reduxForm({
    form: formName,
    fields: validateFormFields,
})
export default class BankEdit extends PureComponent {

    static PropTypes = {
        selected: PropTypes.object,
        banksEdit: PropTypes.function,
        banksUpdate: PropTypes.function,
    };

    constructor(props) {
        super(props);
        this.submitLogin = this.submitLogin.bind(this);
    }

    submitLogin() {
        const {selected, fields: {name}} = this.props;
        this.props.banksUpdate(selected.get('id'), name.value);
    }

    render() {
        const {selected, handleSubmit, fields: {name}} = this.props;
        return (
            <div className="edit-bank-page">
                <h2>Edit {selected.get('id')}</h2>
                <section id="bankform" className="outer-wrapper">
                    <div className="inner-wrapper">
                        <div className="container">
                            <div className="row">
                                <div className="col-sm-4 col-sm-offset-4">
                                    <form onSubmit={handleSubmit(this.submitLogin)}>
                                        <div className="form-group">
                                            <fieldset className="form-group">
                                                <label>Name:</label>
                                                <input {...name} type="text" className="form-control"
                                                       id="exampleInputName1"
                                                       placeholder="Enter new name"/>
                                            </fieldset>
                                        </div>
                                        <button action="submit" className="btn btn-default">Submit</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </section>
            </div>
        );
    }
}