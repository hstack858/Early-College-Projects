import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';

void main() {
  runApp(MaterialApp(home: HomeScreen()));
}

class Product {
  String name;
  int servingNum;
  int servingsLeft;
  double price;

  Product(String name, int servingNum, int servingsLeft, double price) {
    this.name = name;
    this.servingNum = servingNum;
    this.servingsLeft = servingsLeft;
    this.price = price;
  }

  double priceOwed(int servings) {
    double pricePerServing = this.price / this.servingNum;
    return servings * pricePerServing;
  }

  void productUsed(int servings) {
    this.servingsLeft = this.servingsLeft - servings;
  }

  double percentLeft() {
    return (this.servingsLeft / this.servingNum) * 100;
  }
}

Product example = new Product("Banana", 4, 4, 10.00);

class HomeScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        home: Scaffold(
            appBar: AppBar(title: Text('Home Screen')),
            body: Builder(
              builder: (context) => ElevatedButton(
                  onPressed: () {
                    Navigator.push(
                      context,
                      MaterialPageRoute(
                        builder: (context) => UserScreenWidget(),
                      ),
                    );
                  },
                  child: Text('Begin')),
            )));
  }
}

class UserScreenWidget extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    return _UserScreenState();
  }
}

class _UserScreenState extends State<UserScreenWidget> {
  List<Product> masterList;
  double priceOwed;

  _UserScreenState() {
    this.masterList = [];
    this.priceOwed = 0;
  }
  _UserScreenState.withList(List<Product> masterList, double priceOwed) {
    this.masterList = masterList;
    this.priceOwed = priceOwed;
  }

  List<Widget> buildColumn(BuildContext context) {
    List<Widget> list = [];
    for (Product p in this.masterList) {
      String percentString = p.percentLeft().toString();
      if (percentString.length > 7) {
        percentString = percentString.substring(0, 7);
      }
      Widget w = Row(
        mainAxisAlignment: MainAxisAlignment.spaceBetween,
        crossAxisAlignment: CrossAxisAlignment.stretch,
        children: [
          Text(p.name +
              ':  % left = ' +
              percentString +
              ',    Price = ' +
              p.price.toString()),
          ElevatedButton(
              onPressed: () {
                setState(() {
                  this.useServing(p);
                  return new _UserScreenState.withList(
                      this.masterList, this.priceOwed);
                });
              },
              child: Text("Use a serving"))
        ],
      );
      if (p.percentLeft() <= 20) {
        w = Container(color: Colors.red, child: w);
      } else
        w = Container(color: Colors.green, child: w);
      w = Expanded(child: w);
      list.add(w);
    }

    Widget priceOwed = Text('Price owed: ' + this.priceOwed.toString());
    list.add(priceOwed);

    Widget bottomButton = ElevatedButton(
        onPressed: () {
          this.startAddScreen(context);
        },
        child: Text("Add a product"));

    list.add(bottomButton);
    return list;
  }

  void useServing(Product p) {
    p.productUsed(1);
    this.priceOwed = this.priceOwed + p.priceOwed(1);
    if (p.servingsLeft <= 0) {
      this.masterList.remove(p);
    }
  }

  void startAddScreen(BuildContext context) async {
    final result = await Navigator.push(
        context,
        MaterialPageRoute(
          builder: (context) => AddScreen(),
        ));

    setState(() {
      String text = result;
      Product p = new Product("", 0, 0, 0.0);
      int periodIndex = text.indexOf('.');
      p.name = text.substring(0, periodIndex);
      text = text.substring(periodIndex + 1);
      periodIndex = text.indexOf('.');
      p.servingNum = int.parse(text.substring(0, periodIndex));
      text = text.substring(periodIndex + 1);
      periodIndex = text.indexOf('.');
      p.price = double.parse(text.substring(0, periodIndex));
      p.servingsLeft = p.servingNum;

      this.masterList.add(p);

      return new _UserScreenState.withList(this.masterList, this.priceOwed);
    });
  }

  Widget build(BuildContext context) {
    return MaterialApp(
        home: Scaffold(
            appBar: AppBar(
              actions: [
                ElevatedButton(
                    onPressed: () {
                      Navigator.pop(context);
                    },
                    child: Text('Back'))
              ],
            ),
            body: Column(
              children: this.buildColumn(context),
            )));
  }
}

class AddScreen extends StatefulWidget {
  @override
  _AddScreenState createState() {
    return _AddScreenState();
  }
}

class _AddScreenState extends State<AddScreen> {
  TextEditingController textFieldController = TextEditingController();

  void _sendDataBack(BuildContext context) {
    String sendBackText = textFieldController.text;
    Navigator.pop(context, sendBackText);
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
        home: Scaffold(
      appBar: AppBar(title: Text('Add a product')),
      body: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Text(
              'Type in the products name, amount of servings and price. Include periods after each entry. Enter digits for numbers and X.XX. for price'),
          Padding(
            padding: const EdgeInsets.all(32.0),
            child: TextField(
              controller: textFieldController,
              style: TextStyle(
                fontSize: 24,
                color: Colors.black,
              ),
            ),
          ),
          RaisedButton(
            child: Text(
              'Submit',
              style: TextStyle(fontSize: 24),
            ),
            onPressed: () {
              _sendDataBack(context);
            },
          )
        ],
      ),
    ));
  }
}
