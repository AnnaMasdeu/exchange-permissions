# Exchange Permissions

In the finance world companies offer fractions of their ownership known as stocks as a means to receive funding for their future endeavours. A stock entitles its owner to a fraction of the company's annual profit, if there's any to be made. Such a premise does not always hold...

At the other end of the transaction traders buy and sell stocks as an opportunity to earn money, they choose the most likely profitable companies based on a number of factors.

In between both sit the exchanges, market places where publicly listed companies offer their stocks for them to be traded by the general public. The London Stock Exchange (LSE) and the Frankfurt Stock Exchange (FSE) are two examples of exchanges.

Exchanges are themselves companies, and their mission is also to be profitable. Beyond basic stock trading they offer an extra set of services to grow their revenue, such as providing market data feeds.

## Market Data Feeds

A market data feed is a service that provides real life stock information to traders. Market data provide traders a deeper insight into the stocks they're trading and enables them to make strategic decisions. Market data feeds are a monthly subscription based service. Traders pay a monthly fee once when activating the service and are allowed to use it until the end of the next month. At the end of the month users are charged for the next month only if they still have market data feeds enabled. Feeds are also a tiered service, in that there are different levels of service offered, each providing deeper knowledge into their stocks. 

## Market Data Levels

- OFF: As the name suggests when the market data service is not activated it is OFF. Traders usually receive delayed prices when the marked data service is deactivated. Sometimes an aggregation of prices is provided, but never the actual live price.

- L1: Users receive live prices, plus the bid size, ask size, last price and last size.

- L2: Users receive live prices, plus a large part of the order book.

For more info read https://www.thebalance.com/order-book-level-2-market-data-and-depth-of-market-1031118

## Brokers

Most traders don't have the means necessary to trade directly on the exchange, usually they'll go through and intermediary known as a broker that will enable them to access the exchanges services after adding a commission. Luckily for traders, brokers don't usually add a commission to the market data feeds services, after all they want to incentivise trading!
For market data feeds this means brokers will transfer all the money they make from them to the corresponding exchanges. Brokers allow clients to trade on multiple exchanges and so need to keep track of how much money needs to be relayed to each exchange and make sure they charge their clients fairly.

## The mission

eToro, a popular broker in Spain, has asked for a service that will manage their market data subscription service. They want the best developers and they're willing to pay top dollar!
Basic functionality should include:

- Keep track of every users' current market data level
- Bill a user the first time they activate market data feed level in a given month for a given exchange
- Bill a user at the end of the month if they haven't deactivated their subscription
- Only bill the different between L1 and L2 prices if the client upgrades from L1 to L2 (as opposed to going straight to L2)