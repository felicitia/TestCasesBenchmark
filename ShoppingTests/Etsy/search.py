import os
from time import sleep
import unittest

from appium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support.ui import WebDriverWait # available since 2.4.0
from selenium.webdriver.support import expected_conditions as EC # available since 2.26.0

time_out = 5

class SearchTests(unittest.TestCase):

    def setUp(self):
        desired_caps = {
            'platformName': 'android',
            'deviceName': 'Pixel 3 API 23',
            'appPackage': 'com.etsy.android',
            'appActivity': 'com.etsy.android.ui.homescreen.HomescreenTabsActivity'
        }
        self.driver = webdriver.Remote("http://0.0.0.0:4723/wd/hub", desired_caps)

    def tearDown(self):
        self.driver.quit()

    def test_search(self):
        # go to search menu
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.etsy.android:id/menu_search')))
        search_button = self.driver.find_element_by_id('com.etsy.android:id/menu_search')
        search_button.click()
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.etsy.android:id/search_src_text')))
        search_bar = self.driver.find_element_by_id('com.etsy.android:id/search_src_text')
        search_bar.click()

        # search
        search_bar.send_keys('necklace' + '\n')
        #self.driver.press_keycode(66)

        # oracle
        self.assertTrue(self.driver.find_element_by_id('com.etsy.android:id/header_result_count_textview').is_displayed())

    def test_add_cart(self):
        # go to search menu
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.etsy.android:id/menu_search')))
        search_button = self.driver.find_element_by_id('com.etsy.android:id/menu_search')
        search_button.click()
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.etsy.android:id/search_src_text')))
        search_bar = self.driver.find_element_by_id('com.etsy.android:id/search_src_text')
        search_bar.click()

        # search
        search_bar.send_keys('box' + '\n')

        # find item
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.XPATH, '/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.FrameLayout[3]/android.view.ViewGroup')))
        shopping_item = self.driver.find_element_by_xpath('/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.FrameLayout[3]/android.view.ViewGroup')

        # testing...
        shopping_item.click()

        # find add cart
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.etsy.android:id/button_cart')))
        add_cart = self.driver.find_element_by_id('com.etsy.android:id/button_cart')

        # testing...
        add_cart.click()

        # oracle
        # EditText.setError() isn't supported

    def test_add_remove_cart(self):
        # go to search menu
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.etsy.android:id/menu_search')))
        search_button = self.driver.find_element_by_id('com.etsy.android:id/menu_search')
        search_button.click()
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.etsy.android:id/search_src_text')))
        search_bar = self.driver.find_element_by_id('com.etsy.android:id/search_src_text')
        search_bar.click()

        # search
        search_bar.send_keys('box' + '\n')

        # open item
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.XPATH, '/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.FrameLayout[3]/android.view.ViewGroup')))
        shopping_item = self.driver.find_element_by_xpath('/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.FrameLayout[3]/android.view.ViewGroup')
        shopping_item.click()

        # add item to cart
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.etsy.android:id/button_cart')))
        add_cart = self.driver.find_element_by_id('com.etsy.android:id/button_cart')
        add_cart.click()

        # open cart
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.etsy.android:id/cart_icon')))
        cart = self.driver.find_element_by_id('com.etsy.android:id/cart_icon')
        cart.click()

        # find elements
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.etsy.android:id/btn_remove')))
        remove_btn = self.driver.find_element_by_id('com.etsy.android:id/btn_remove')

        # testing...
        remove_btn.click()


if __name__ == '__main__':
    suite = unittest.TestLoader().loadTestsFromTestCase(SearchTests)
    unittest.TextTestRunner(verbosity=2).run(suite)
