import React from 'react';
import type {Node} from 'react';

//  import type {Node} from 'react';
import {
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  useColorScheme,
  View,
  requireNativeComponent,
  TextInput,
  Button,
  onChangeText,
} from 'react-native';

import {
  Colors,
  DebugInstructions,
  Header,
  LearnMoreLinks,
  ReloadInstructions,
} from 'react-native/Libraries/NewAppScreen';
import {useState, useEffect} from 'react';
import {findCategories} from './service/answer.service';

function App() {
  let [search, setSearch] = useState({
    start: 0,
    length: 10,
    keyword: '',
  });

  let [result, setResult] = useState({
    recordsTotal: 0,
    recordsFiltered: 0,
    data: [],
  });

  let [success, setSuccess] = useState(false);

  useEffect(() => {
    const timeout = setTimeout(() => {
      //goi lai
      find();
    }, 500);
    return () => clearTimeout(timeout);
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [search]);

  const find = async () => {
    let {code, result} = await findCategories(search);
    if (code === 200) {
      setResult(result);
    }
  };

  const handleChange = e =>
    setSearch({
      ...search,
      [e.target.name]: e.target.value,
    });
  return (
    <>
      <View>
        <Text>Search Category</Text>
      </View>
      <View>
        <TextInput onChangeText={handleChange} />
        <Button title="Click" onPress={find}>
          {'search'}
        </Button>
      </View>
      <View>
        <Text>Id</Text>
        <Text>Name</Text>
        <Text>Option</Text>
      </View>

      {/* //   <View>
      //     {result.data.map(({id, name}) => (
      //       <View key={id}>
      //         <View>{id}</View>
      //         <View>{name}</View>
      //       </View>
      //     ))} 
      //   </View>
      // </View> */}
    </>
  );
}

export default App;
