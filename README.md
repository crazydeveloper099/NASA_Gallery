# exercise_app
An application that provides with the latest NASA findings with proper description, images, copyright and dates fetched from a JSON file.
<h1>Features</h1>
<h3>1- Staggered Grid View with smooth rearrange animations on scroll and stuffed with fresco library to make image loading a cake walk. </h3>
<p float="left">
  <img src="https://drive.google.com/uc?id=1-y48fhonae---LUhZNGnGWS5-MFl--mt" width="400" />
  <img src="https://drive.google.com/uc?id=1-uJVoI6XNDYvk3p4UMXzjJnmKfSegiO-" width="400" /> 
</p>
<h3>2- Three types of details section with haptic feedback on image change and swipe to dismiss feature. </h3>
<p float="left">
  <img src="https://drive.google.com/uc?id=1-TxTqCqexw7PVcIXVczeKjvlwWSyshxR" width="270" />
  <img src="https://drive.google.com/uc?id=1-UW1HBbVyDE7XgEJHjAl3HyCFVSvCw5q" width="270" /> 
  <img src="https://drive.google.com/uc?id=105hMYlFj9tSnLcP6fl3FQjnkURH-sJA8" width="270" /> 

</p>
<h1>Description</h1>
<h3>1- The app uses MVVM architecture.</h3>
<h3>2- The MainActivity containing recyclerView using "StaggeredGridLayoutManager" as its LayoutManager.</h3>
<h3>3- The RecyclerAdapter gets arraylist populated by observing LiveData from ViewModel calss</h3>
<h3>4- MainActivity contains a showFragment() function that has ImageViewer library that implements viewPager and uses Fresco to display images its position and if it is shown or not is however saved in savedInstanceState bundle to return to same state when rotation or other config change happens. </h3>
<h3>5- The GridViewModel is a ViewModel class that observes from ImageRepository class and returns a LiveData object for main activity to observe</h3>
<h3>6- The ImageRepository is a Singleton class that parses the JSON file "data.json" using "JSONLoader" library and returns a MutableLiveData for ViewModel to observe. It also contatins a comparator to sort the data in list populated according to dates.</h3>
<h3>7- There are two Model classes ImageModel and ImageOverlayModel, ImageModel holds the data for "grid_item.xml" and ImageOverlayModel holds data for "image_overlay.xml" item.</h3>
<h3>8- The "imageRecyclerViewAdapter" serves as the RecyclerView adapter class.</h3>
<h3>9- The "WrapContentDraweeView" class extends the SimpleDraweeView of Fresco to simply get the height of the image and make wrap_content attribute compatible.</h3>




