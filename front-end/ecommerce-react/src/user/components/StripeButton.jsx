import {useState} from "react";
import StripeCheckout from "react-stripe-checkout";
import axios from "axios";
import Swal from "sweetalert2";

const StripeButton = ({ price, subscriptionType }) => {
    const publishableKey = "pk_test_51OjLFIJTJ8t3F6R1B5y5modvKooetBwSlRwidoSj6wCs6xl2Aoawq7ViMrvDiwRnFcbibBUwf6LB1vYJc6Wd4vgV00zv4f1Mcx";
    const stripePrice = price * 100;
    const username = localStorage.getItem('username');
    const [error, setError] = useState(null);

    const onToken = (token) => {
    axios
      .post("http://localhost:8080/api/stripe/payment", {
            amount: stripePrice,
            token: token.id,
        }, {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`
        }
      })
      .then((response) => {
        axios
            .post("http://localhost:8080/api/subscription/succeeded", {
                    subscriptionType: subscriptionType,
                }, {
                    headers: {
                        Authorization: `Bearer ${localStorage.getItem('token')}`
                }
            })
            .then((response) => {
                Swal.fire({
                    title: "Payment Successful!",
                    text: "Please click OK to continue.",
                    icon: "success",
                    didClose: () => {
                        window.location.href ='/user';
                    }
                  });
            });
      })
      .catch((error) => {
        setError(error.response.data.text);
        Swal.fire({
            title: "Payment failed!",
            text: "Please click OK to continue.",
            icon: "error",
            didClose: () => {
                window.location.href ='/user';
            }
          });
      });
  };

  return (
    <>
        <StripeCheckout
        amount={stripePrice}
        label="BUY"
        name= {username}
        image="https://svgshare.com/i/CUz.svg"
        description={`Your total is ${price}`}
        panelLabel="Pay Now"
        token={onToken}
        stripeKey={publishableKey}
        currency="USD"
        />
        <div>
            {error && <p>Error: {error}</p>}
        </div>
    </>
  );
};

export default StripeButton;