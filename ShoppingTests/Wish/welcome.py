import os
from time import sleep
import unittest

from appium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait # available since 2.4.0
from selenium.webdriver.support import expected_conditions as EC # available since 2.26.0

time_out = 5

class WelcomeTests(unittest.TestCase):

    def setUp(self):
        desired_caps = {
            'platformName': 'android',
            'deviceName': 'Pixel 3 API 23',
            'appPackage': 'com.contextlogic.wish',
            'appActivity': 'com.contextlogic.wish.activity.browse.BrowseActivity'
        }
        self.driver = webdriver.Remote("http://0.0.0.0:4723/wd/hub", desired_caps)

    def tearDown(self):
        self.driver.quit()

    # test: sign in empty
    def sign_in_empty(self):
        self.sign_in('', '')

    # test: sign in wrong
    def sign_in_wrong(self):
        self.sign_in('aaa', 'bbb')

    def sign_in(self, usrname_input, pwd_input):
        # input events: sign in by usrname_input and pwd_input
        WebDriverWait(self.driver, time_out).until(
            EC.presence_of_element_located((By.ID, 'com.contextlogic.wish:id/sign_in_fragment_email_text')))
        WebDriverWait(self.driver, time_out).until(
            EC.presence_of_element_located((By.ID, 'com.contextlogic.wish:id/sign_in_fragment_password_text')))
        WebDriverWait(self.driver, time_out).until(
            EC.presence_of_element_located((By.ID, 'com.contextlogic.wish:id/sign_in_fragment_sign_in_button')))
        email = self.driver.find_element_by_id('com.contextlogic.wish:id/sign_in_fragment_email_text')
        password = self.driver.find_element_by_id('com.contextlogic.wish:id/sign_in_fragment_password_text')
        sign_in = self.driver.find_element_by_id('com.contextlogic.wish:id/sign_in_fragment_sign_in_button')
        email.send_keys(usrname_input)
        password.send_keys(pwd_input)

        # assert events: click sign in button
        sign_in.click()
        # oracle: check if wrong sign in message is displayed
        # self.assertTrue(self.driver.find_element_by_id(
        #     'com.contextlogic.wish:id/multi_button_dialog_fragment_content').is_displayed())

    # test: forget password
    def forget_pwd(self):
        # assert events: click forget pwd
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.contextlogic.wish:id/sign_in_fragment_forgot_password_button')))
        forget_pwd = self.driver.find_element_by_id('com.contextlogic.wish:id/sign_in_fragment_forgot_password_button')
        forget_pwd.click()
        # oracle: check if forget pwd dialog is displayed
        # self.assertTrue(self.driver.find_element_by_id('com.contextlogic.wish:id/forgot_password_fragment_reset_password_button').is_displayed())

    def transit_back(self):
        WebDriverWait(self.driver, time_out).until(
            EC.presence_of_element_located((By.XPATH, '/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.Button')))
        ok_btn = self.driver.find_element_by_xpath('/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.LinearLayout/android.widget.Button')
        ok_btn.click()

    # user session
    def test_welcome(self):
        current_dir = '%s/' % os.getcwd() + 'welcome.'

        # test: sign in empty
        self.sign_in_empty()
        screenshot_name = 'sing_in_empty.png'
        self.driver.save_screenshot(current_dir + screenshot_name)
        self.transit_back()

        # test: sign in wrong
        self.sign_in_wrong()
        screenshot_name = 'sign_in_wrong.png'
        self.driver.save_screenshot(current_dir + screenshot_name)
        self.transit_back()

        # test: forget password
        self.forget_pwd()
        screenshot_name = 'forget_pwd.png'
        self.driver.save_screenshot(current_dir + screenshot_name)

if __name__ == '__main__':
    suite = unittest.TestLoader().loadTestsFromTestCase(WelcomeTests)
    unittest.TextTestRunner(verbosity=2).run(suite)
