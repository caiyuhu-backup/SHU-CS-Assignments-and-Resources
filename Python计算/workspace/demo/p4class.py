class Lunch:
    def __init__(self):
        self.cust = Customer()
        self.empl = Employee()

    def order(self, foodName):
        self.cust.placeOrder(foodName, self.empl)

    def result(self):
        self.cust.printFood()


class Customer:
    def __init__(self):
        self.food = None

    def placeOrder(self, foodName, employee):
        self.food = employee.takeOrder(foodName)

    def printFood(self):
        print(self.food.name)


class Employee:
    def takeOrder(self, foodName):
        return Food(foodName)


class Food:
    def __init__(self, name):
        self.name = name


'''
if __name__ == '__main__':
    x = Lunch()
    x.order('burritos')
    x.result()
    x.order('pizza')
    x.result()
'''
