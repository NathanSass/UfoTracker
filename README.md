# UfoTracker

### Description
This project tracks UFO sightings! I focused on implementing the app quickly while considering possible ways to scale.

### How to use
- Tap plus button in action bar to add a record
- Long click a cell to show / hide a delete button. Click on delete to delete the cell.

### Technologies Used
- Android ViewModel
- LiveData
- Custom Views
- RecyclerView
- DividerItemDecoration
- ViewBinding
- ViewPager2 / FragmentStateAdapter / TabLayoutMediator
- Activity / Fragment

### Design Decisions 
- MVVM was mostly used through out. Some corners were cut due to time
- A `Repository` was implemented to handle data fetch. `UfoModulesViewModel` subscribes to it and receives data updates
- A fragment subscribes to the entire data stream and then filters out the types of UFO sightings it is responsible for showing
- Cell is a custom view
- Time is stored as a timestamp

### Known issues
- Delete experience could be adjusted
- UI issue with displaying minutes. Minutes less than 10 are displayed with just one digit. ex. 6 min vs 06 min

I spent about 3 hours on the challenge


