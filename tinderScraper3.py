from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from webdriver_manager.chrome import ChromeDriverManager
import time
import random
import requests

running = True

class TinderScraper:

    def __init__(self):
        option = webdriver.ChromeOptions()

        # Removes navigator.webdriver flag

        option.add_argument('--disable-blink-features=AutomationControlled')
        option.add_argument('--ignore-certificate-errors')
        option.add_argument('--ignore-ssl-errors')
        option.add_argument("--start-maximized")
        self.driver = webdriver.Chrome(ChromeDriverManager().install(), options=option)

    ## Writes the data from a single profile to the file
    def getData(self, fileName):

        f = open(fileName, "a")
        nameB = False
        ageB = False
        bioB = False
        picB = False
        infoB = False
        infoB2 = False
        name = ""
        age = ""
        bio = ""
        pic = ""

        ## Tries to get the data from the outside of the profile first
        try:
            name = self.driver.find_element_by_xpath('//*[@id="content"]/div/div[1]/div/main/div[1]/div/div/div[1]/div[1]/div[1]/div[3]/div[3]/div/div[1]/div/div/span').text
        except Exception:
            nameB = True

        try:
            age = self.driver.find_element_by_xpath('//*[@id="content"]/div/div[1]/div/main/div[1]/div/div/div[1]/div[1]/div[1]/div[3]/div[3]/div/div[1]/div/span[2]').text
        except Exception:
            ageB = True

        try:
            bio = self.driver.find_element_by_xpath('//*[@id="content"]/div/div[1]/div/div/main/div/div[1]/div[1]/div[1]/div[3]/div[3]/div/div[2]/div/div').text
        except Exception:
            try:
                 bio = self.driver.find_element_by_xpath('//*[@id="content"]/div/div[1]/div/div/main/div/div[1]/div[1]/div[1]/div[3]/div[3]/div/div[2]/div/div/text()')
            except Exception:
                try: 
                    bio = self.driver.find_element_by_xpath('//*[@id="content"]/div/div[1]/div/main/div[1]/div/div/div[1]/div[1]/div[1]/div[3]/div[3]/div/div[2]/div/div').text
                except Exception:
                    try:
                        bio = self.driver.find_element_by_xpath('//*[@id="content"]/div/div[1]/div/main/div[1]/div/div/div[1]/div[1]/div[1]/div[3]/div[3]/div/div[2]/div/div/text()')
                    except Exception:
                        try:
                            bio = self.driver.find_element_by_xpath('//*[@id="content"]/div/div[1]/div/main/div[1]/div/div/div[1]/div[1]/div[1]/div[3]/div[4]/div/div[2]/div/div[1]').text
                        except Exception:
                            try:
                                bio = self.driver.find_element_by_xpath('//*[@id="content"]/div/div[1]/div/main/div[1]/div/div/div[1]/div[1]/div/div[2]/div[2]/div').text
                            except Exception:
                                bioB = True


        try:
            pic = self.driver.find_element_by_xpath('//*[@id="content"]/div/div[1]/div/main/div[1]/div/div/div[1]/div[1]/div[1]/div[3]/div[1]/div[1]/span[1]/div').get_attribute("style")
        except Exception:
            picB = True

        if (nameB or ageB or bioB or picB):
            infoB = True
        time.sleep(1)

        ## Clicks on the arrow to get inside the profile if all of the data was not able to be extracted from the outside
        if (infoB):
            try:
                infoButton = self.driver.find_element_by_xpath('//*[@id="content"]/div/div[1]/div/main/div[1]/div/div/div[1]/div[1]/div[1]/div[3]/div[3]/button')
                infoButton.click()
                if (nameB):
                    try:
                        name = self.driver.find_element_by_xpath('//*[@id="content"]/div/div[1]/div/main/div[1]/div/div/div[1]/div[1]/div/div[2]/div[1]/div/div[1]/div/h1').text
                    except Exception:
                        name = "Has no name"
                if (ageB):
                    try:
                        age = self.driver.find_element_by_xpath('//*[@id="content"]/div/div[1]/div/main/div[1]/div/div/div[1]/div[1]/div/div[2]/div[1]/div/div[1]/span').text
                    except Exception:
                        age = "Has no age"
                if (picB):
                    try:
                        pic = self.driver.find_element_by_xpath('//*[@id="content"]/div/div[1]/div/main/div[1]/div/div/div[1]/div[1]/div/div[1]/span/div/div[1]/span[1]/div/div').get_attribute("style")
                    except Exception:
                        pic = "Has no pic"
                if (bioB):
                    self.driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
                    try:
                        bio = self.driver.find_element_by_xpath('//*[@id="content"]/div/div[1]/div/main/div[1]/div/div/div[1]/div[1]/div/div[2]/div[2]/div').text
                    except Exception:
                        try:
                            bio = self.driver.find_element_by_xpath('//*[@id="content"]/div/div[1]/div/div/main/div/div/div[1]/div/div[2]/div[2]/div').text
                        except Exception:
                            try:
                                bio = self.driver.find_element_by_xpath('//*[@id="content"]/div/div[1]/div/div/main/div/div/div[1]/div/div[2]/div[2]/div/text()')
                            except Exception:
                                try:
                                    bio = self.driver.find_element_by_xpath('//*[@id="content"]/div/div[1]/div/main/div[1]/div/div/div[1]/div[1]/div/div[2]/div[2]/div/text()')
                                except Exception:
                                    bio = "Has no bio"
            except Exception:
                try:
                    infoButton = self.driver.find_element_by_xpath('//*[@id="content"]/div/div[1]/div/main/div[1]/div/div/div[1]/div/div[1]/div[3]/div[4]/button')
                    infoButton.click()
                    if (nameB):
                        try:
                            name = self.driver.find_element_by_xpath('//*[@id="content"]/div/div[1]/div/main/div[1]/div/div/div[1]/div[1]/div/div[2]/div[1]/div/div[1]/div/h1').text
                        except Exception:
                            name = "Has no name"
                    if (ageB):
                        try:
                            age = self.driver.find_element_by_xpath('//*[@id="content"]/div/div[1]/div/main/div[1]/div/div/div[1]/div[1]/div/div[2]/div[1]/div/div[1]/span').text
                        except Exception:
                            age = "Has no age"
                    if (picB):
                        try:
                            pic = self.driver.find_element_by_xpath('//*[@id="content"]/div/div[1]/div/main/div[1]/div/div/div[1]/div[1]/div/div[1]/span/div/div[1]/span[1]/div/div').get_attribute("style")
                        except Exception:
                            pic = "Has no pic"
                    if (bioB):
                        self.driver.execute_script("window.scrollTo(0, document.body.scrollHeight);")
                        try:
                            bio = self.driver.find_element_by_xpath('//*[@id="content"]/div/div[1]/div/main/div[1]/div/div/div[1]/div[1]/div/div[2]/div[2]/div').text
                        except Exception:
                            try:
                                bio = self.driver.find_element_by_xpath('//*[@id="content"]/div/div[1]/div/div/main/div/div/div[1]/div/div[2]/div[2]/div').text
                            except Exception:
                                try:
                                    bio = self.driver.find_element_by_xpath('//*[@id="content"]/div/div[1]/div/div/main/div/div/div[1]/div/div[2]/div[2]/div/text()')
                                except Exception:
                                    try:
                                        bio = self.driver.find_element_by_xpath('//*[@id="content"]/div/div[1]/div/main/div[1]/div/div/div[1]/div[1]/div/div[2]/div[2]/div/text()')
                                    except Exception:
                                        bio = "Has no bio"
                except Exception:
                    infoB2 = True
        
        if (name == ""):
            name = "Has no name"
        if (age == ""):
            age = "Has no age"
        if (bio == ""):
            bio = "Has no bio"
        if (pic == ""):
            pic = "Has no first picture"
        
        try:
            f.write(name)
        except Exception:
            f.write("Has no name")
        f.write("\n")

        try:
            f.write(age)
        except Exception:
            f.write("Has no age")
        f.write("\n")

        try:
            f.write(bio)
        except Exception:
            f.write("Has no bio")
        f.write("\n")

        try:
            f.write(pic)
        except Exception:
            f.write("Has no first picture")
        f.write("\n")

        if (infoB2):
            f.write('Couldnt get into more info section')
            f.write("\n")

        f.close()


    def swipeRight(self):
        try:                                                
            likeButton = self.driver.find_element_by_xpath('//*[@id="content"]/div/div[1]/div/main/div[1]/div/div/div[1]/div[1]/div[2]/div[2]/button')
            likeButton.click()
        except Exception:
            try:
                likeButton = self.driver.find_element_by_xpath('//*[@id="content"]/div/div[1]/div/main/div[1]/div/div/div[1]/div[2]/div/div/div[4]/button')
                likeButton.click()
            except Exception:
                likeButton = self.driver.find_element_by_xpath('//*[@id="content"]/div/div[1]/div/main/div[1]/div/div/div[1]/div/div[2]/div[4]/button')
                likeButton.click()
        

    def swipeLeft(self):
        try:
            dislikeButton = self.driver.find_element_by_xpath('//*[@id="content"]/div/div[1]/div/main/div[1]/div/div/div[1]/div/div[2]/div[2]/button')
            dislikeButton.click()
        except Exception:
            try:
                dislikeButton = self.driver.find_element_by_xpath('//*[@id="content"]/div/div[1]/div/main/div[1]/div/div/div[1]/div[2]/div/div/div[2]/button')
                dislikeButton.click()
            except Exception:
                dislikeButton = self.driver.find_element_by_xpath('//*[@id="content"]/div/div[1]/div/main/div[1]/div/div/div[1]/div/div[2]/div[2]/button')
                dislikeButton.click()


    def closeMatch(self):
        keepSwipingButton = self.driver.find_element_by_xpath('//*[@id="modal-manager-canvas"]/div/div/div[1]/div/div[4]/button')
        keepSwipingButton.click()

    def platPopup(self):
        popup = self.driver.find_element_by_xpath('//*[@id="modal-manager"]/div/div/button[2]')
        popup.click()

    def closeSuperLike(self):
        closeButton = self.driver.find_element_by_xpath('//*[@id="modal-manager"]/div/div/button[2]')
        closeButton.click()

    def closeHomeScreen(self):
        notInterested = self.driver.find_element_by_xpath('//*[@id="modal-manager"]/div/div/div[2]/button[2]')
        notInterested.click()

    def autoSwipe(self, fileName):
        count = 0
        while running:
            self.getData(fileName)
            time.sleep(2)
            randNum = random.uniform(0, 1)

            ## Left swipe decision tree
            if randNum > 0.72:
                try:
                    self.swipeLeft()
                except Exception:
                    try:
                        self.closeMatch()
                    except Exception:
                        try:
                            self.closeSuperLike()
                        except Exception:
                            try:
                                self.closeHomeScreen()
                            except Exception:
                                try:
                                    self.platPopup()
                                except Exception:
                                    count = count + 1
                                    print("Not a screen this bot can get through... YET. Occurence #" + str(count))
            
            ## Right swipe decision tree
            else:
                try:
                    self.swipeRight()
                except Exception:
                    try:
                        self.closeMatch()
                    except Exception:
                        try:
                            self.closeSuperLike()
                        except Exception:
                            try:
                                self.closeHomeScreen()
                            except Exception:
                                try:
                                    self.platPopup()
                                except Exception:
                                    count = count + 1
                                    print("Not a screen this bot can get through... YET. Occurence #" + str(count))
