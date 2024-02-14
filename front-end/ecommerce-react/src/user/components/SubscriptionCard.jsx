import React from "react";
import "./SubscriptionCard.css";
import StripeButton from "./StripeButton";

const SubscriptionCard = ({ title, price, subscriptionType}) => {
  return (
    <div className="PricingCard">
      <header>
        <h1 className="card-title">{title}</h1>
        <h1 className="card-price">$ {price}</h1>
      </header>
      <StripeButton
        className="card-btn"
        price={price}
        subscriptionType={subscriptionType}
      />
    </div>
  );
};
export default SubscriptionCard;

