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
        # user inputs
        search_item = 'necklace'

        # transition events: go to search menu
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.etsy.android:id/menu_search')))
        search_button = self.driver.find_element_by_id('com.etsy.android:id/menu_search')
        search_button.click()
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.etsy.android:id/search_src_text')))
        search_bar = self.driver.find_element_by_id('com.etsy.android:id/search_src_text')
        search_bar.click()

        # input events: search by search_item
        # assert events: search by search_item
        search_bar.send_keys(search_item + '\n')
        # oracle: check if search results are displayed
        self.assertTrue(self.driver.find_element_by_id('com.etsy.android:id/header_result_count_textview').is_displayed())

    def test_add_cart(self):
        # user inputs
        search_item = 'box'
        item_idx = '3'

        # transition events: go to search menu
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.etsy.android:id/menu_search')))
        search_button = self.driver.find_element_by_id('com.etsy.android:id/menu_search')
        search_button.click()
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.etsy.android:id/search_src_text')))
        search_bar = self.driver.find_element_by_id('com.etsy.android:id/search_src_text')
        search_bar.click()

        # input events: search by search_item
        search_bar.send_keys(search_item + '\n')

        # input events: find item by item_idx
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.XPATH, '/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.FrameLayout[' + item_idx + ']/android.view.ViewGroup')))
        shopping_item = self.driver.find_element_by_xpath('/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.FrameLayout[' + item_idx + ']/android.view.ViewGroup')
        shopping_item.click()

        # assert events: click add cart button
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.etsy.android:id/button_cart')))
        add_cart = self.driver.find_element_by_id('com.etsy.android:id/button_cart')
        add_cart.click()
        # oracle: check if item is added

    def test_add_remove_cart(self):
        #user inputs
        search_item = 'box'
        item_idx = '2'

        # transition events: go to search menu
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.etsy.android:id/menu_search')))
        search_button = self.driver.find_element_by_id('com.etsy.android:id/menu_search')
        search_button.click()
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.etsy.android:id/search_src_text')))
        search_bar = self.driver.find_element_by_id('com.etsy.android:id/search_src_text')
        search_bar.click()

        # input events: search by search_item
        search_bar.send_keys(search_item + '\n')

        # input events: find item by item_idx
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.XPATH, '/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.FrameLayout[' + item_idx + ']/android.view.ViewGroup')))
        shopping_item = self.driver.find_element_by_xpath('/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.support.v4.widget.DrawerLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.RelativeLayout/android.view.ViewGroup/android.support.v7.widget.RecyclerView/android.widget.FrameLayout[' + item_idx + ']/android.view.ViewGroup')
        shopping_item.click()

        # transition events: click add cart button & open cart
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.etsy.android:id/button_cart')))
        add_cart = self.driver.find_element_by_id('com.etsy.android:id/button_cart')
        add_cart.click()
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.etsy.android:id/cart_icon')))
        cart = self.driver.find_element_by_id('com.etsy.android:id/cart_icon')
        cart.click()

        # assert events: click remove from cart button
        WebDriverWait(self.driver, time_out).until(EC.presence_of_element_located((By.ID, 'com.etsy.android:id/btn_remove')))
        remove_btn = self.driver.find_element_by_id('com.etsy.android:id/btn_remove')
        remove_btn.click()
        # oracle: check if item is removed


if __name__ == '__main__':
    suite = unittest.TestLoader().loadTestsFromTestCase(SearchTests)
    unittest.TextTestRunner(verbosity=2).run(suite)
