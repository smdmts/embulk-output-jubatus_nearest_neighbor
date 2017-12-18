# Jubatus Nearest Neighbor Set output plugin for Embulk

TBD

## Overview

* **Plugin type**: output
* **Load all or nothing**: no
* **Resume supported**: no
* **Cleanup supported**: yes

## Configuration

- **option1**: description (integer, required)
- **option2**: description (string, default: `"myvalue"`)
- **option3**: description (string, default: `null`)

## Example

```yaml
out:
  type: jubatus_nearest_neighbor_set
  option1: example1
  option2: example2
```


## Build

```
$ ./gradlew gem  # -t to watch change of files and rebuild continuously
```
# jubatus_nearest_neighbor
