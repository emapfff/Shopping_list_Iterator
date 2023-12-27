# Description:
This application allows customers to purchase items from a grocery shop. The list of available
items can be found in a shop.txt file. The format of the data is as follows: (bread, 10),
(milk, 20), (apples, 16), where the first item in the parentheses is the name of the
item, and the second is the quantity. <br />
In addition, there are several shopping lists from customers that look the same, such as
(lemon, 1), (cheese, 1), and so on. When a customer wants to buy something, the
iterator runs through all the possible items in the shop and checks if the product exists. If it does,
the quantity decreases according to the amount bought. However, if the product does not exist or
there is not enough quantity, an error message will be printed on the screen.<br />
## To do
- Use the Iterator design pattern to implement this app.
- Employ lazy loading to hide the process of reading the grocery items list from the
shop.txt file from the client.
- Implement a mechanism to handle cases when a product is out of stock and display an
appropriate message on the screen.
- Ensure that the Iterator class is iterable via a Foreach loop.
