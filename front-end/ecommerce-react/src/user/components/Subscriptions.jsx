import SubscriptionCard from "./SubscriptionCard";
import "./SubscriptionCard.css";

export const Subscriptions = (subscriptionsList) =>{

    const yearlySubscription = subscriptionsList != null ? subscriptionsList.subscriptionsList.find(subscription => subscription.subscriptionType === 'YEARLY') : null;
    const monthlySubscription = subscriptionsList != null ? subscriptionsList.subscriptionsList.find(subscription => subscription.subscriptionType === 'MONTHLY') : null;

    return (
        <div className="PricingApp">
        <div className="app-container">
            <header>
            <h1 className="header-topic">Our subscriptions</h1>
            </header>
            <div className="pricing-cards">
            <SubscriptionCard
                title= {`${monthlySubscription.description} sub`}
                price={monthlySubscription.price}
                subscriptionType = {monthlySubscription.subscriptionType}
            />
            <SubscriptionCard
                title= {`${yearlySubscription.description} sub`}
                price={yearlySubscription.price}
                subscriptionType={yearlySubscription.subscriptionType}
            />
            </div>
        </div>
        </div>
    );
}
