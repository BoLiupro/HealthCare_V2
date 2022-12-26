from flask import Flask
from flask_restful import Api

import lifeIndex,epidamic

app = Flask(__name__)
api = Api(app)

index1=lifeIndex.dic
index2=epidamic.dic

@app.route('/lifeIndex')
def weather():
    return index1

@app.route('/epidamic')
def epidamic():
    return index2

# @app.route('/price')
# def price():
#     return index3

if __name__ == '__main__':
    app.run()