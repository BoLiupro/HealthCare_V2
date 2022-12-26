import datetime

import requests
import re

url=" https://voice.baidu.com/act/newpneumonia/newpneumonia"
headers={
    "User-Agent": "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.0.3 Safari/605.1.15"
}
resp=requests.get(url=url, headers=headers )
resp.encoding = 'utf-8'

str=resp.text
obj=re.compile(r'summaryDataIn":\{"confirmed":"(?P<confirmed>.*?)","died":"(?P<died>.*?)"'
               r',"cured":"(?P<cured>.*?)","asymptomatic":"(?P<asymptonmatic>.*?)".*?'
               r'"overseasInput":"(?P<overseainput>.*?)".*?"share')
datas = obj.finditer(str)
dic={}
for it in datas:
    dic["时间"]=datetime.datetime.now().strftime('%Y-%m-%d %H:%M:%S')
    dic["确诊"]=it.group("confirmed")
    dic["死亡"]=it.group("died")
    dic["治愈"]=it.group("cured")
    dic["无症状感染"]=it.group("asymptonmatic")
    dic["境外输入"]=it.group("overseainput")

