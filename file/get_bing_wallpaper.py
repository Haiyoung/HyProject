# -*- coding: utf-8 -*-

import os
import sys
import shutil
import requests
from datetime import datetime
import xml.etree.ElementTree

print("当前时间：%s" % (datetime.now().strftime("%Y-%m-%d %H:%M:%S")))

def get_image():
    count = 0
    for i in range(8):
        url = "http://az517271.vo.msecnd.net/TodayImageService.svc/HPImageArchive?mkt=zh-cn&idx=" + str(i)
        r = requests.get(url)
        if r.status_code == 200:
            with open("temp.xml", "w", encoding="utf-8") as f:
                f.write(str(r.text))
            e = xml.etree.ElementTree.parse('temp.xml').getroot()
            image_url = e[6].text
            image_name = image_url.split('/')[-1]
            if not os.path.exists(image_name):
                image_data = requests.get(image_url, stream=True)
                with open(image_name, 'wb') as f:
                    shutil.copyfileobj(image_data.raw, f)
                del image_data
                count = count + 1
                print("Image list: %s\n" %(image_name))
            os.remove('temp.xml')
        else:
            sys.exit("")
    print("count: %d" % (count,))


def main():
    user_home = os.environ.get("USERPROFILE")
    bing_path = user_home + r"\Pictures\Bing"
    os.makedirs(bing_path, exist_ok=True)
    os.chdir(bing_path)
    get_image()


if __name__ == "__main__":
    main()
