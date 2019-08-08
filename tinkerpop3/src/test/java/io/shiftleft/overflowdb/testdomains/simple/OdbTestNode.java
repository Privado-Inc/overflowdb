package io.shiftleft.overflowdb.testdomains.simple;

import io.shiftleft.overflowdb.NodeFactory;
import io.shiftleft.overflowdb.NodeLayoutInformation;
import io.shiftleft.overflowdb.NodeRef;
import io.shiftleft.overflowdb.OdbGraph;
import io.shiftleft.overflowdb.OdbNode;
import io.shiftleft.overflowdb.OdbNodeProperty;
import org.apache.tinkerpop.gremlin.structure.VertexProperty;
import org.apache.tinkerpop.gremlin.util.iterator.IteratorUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class OdbTestNode extends OdbNode {
  public static final String LABEL = "testNode";

  public static final String STRING_PROPERTY = "StringProperty";
  public static final String INT_PROPERTY = "IntProperty";
  public static final String STRING_LIST_PROPERTY = "StringListProperty";
  public static final String INT_LIST_PROPERTY = "IntListProperty";

  /* properties */
  private String stringProperty;
  private Integer intProperty;
  private List<String> stringListProperty;
  private List<Integer> intListProperty;

  protected OdbTestNode(NodeRef ref) {
    super(ref);
  }

  @Override
  public String label() {
    return OdbTestNode.LABEL;
  }

  @Override
  protected NodeLayoutInformation layoutInformation() {
    return layoutInformation;
  }

  /* note: usage of `==` (pointer comparison) over `.equals` (String content comparison) is intentional for performance - use the statically defined strings */
  @Override
  protected <V> Iterator<VertexProperty<V>> specificProperties(String key) {
    if (STRING_PROPERTY.equals(key) && stringProperty != null) {
      return IteratorUtils.of(new OdbNodeProperty(this, key, stringProperty));
    } else if (key == STRING_LIST_PROPERTY && stringListProperty != null) {
      return IteratorUtils.of(new OdbNodeProperty(this, key, stringListProperty));
    } else if (key == INT_PROPERTY && intProperty != null) {
      return IteratorUtils.of(new OdbNodeProperty(this, key, intProperty));
    } else if (key == INT_LIST_PROPERTY && intListProperty != null) {
      return IteratorUtils.of(new OdbNodeProperty(this, key, intListProperty));
    } else {
      return Collections.emptyIterator();
    }
  }

  @Override
  public Map<String, Object> valueMap() {
    Map<String, Object> properties = new HashMap<>();
    if (stringProperty != null) properties.put(STRING_PROPERTY, stringProperty);
    if (stringListProperty != null) properties.put(STRING_LIST_PROPERTY, stringListProperty);
    if (intProperty != null) properties.put(INT_PROPERTY, intProperty);
    if (intListProperty != null) properties.put(INT_LIST_PROPERTY, intListProperty);
    return properties;
  }

  @Override
  protected <V> VertexProperty<V> updateSpecificProperty(
      VertexProperty.Cardinality cardinality, String key, V value) {
    if (STRING_PROPERTY.equals(key)) {
      this.stringProperty = (String) value;
    } else if (STRING_LIST_PROPERTY.equals(key)) {
      if (value instanceof List) {
        this.stringListProperty = (List) value;
      } else {
        if (this.stringListProperty == null) this.stringListProperty = new ArrayList<>();
        this.stringListProperty.add((String) value);
      }
    } else if (INT_PROPERTY.equals(key)) {
      this.intProperty = (Integer) value;
    } else if (INT_LIST_PROPERTY.equals(key)) {
      if (value instanceof List) {
        this.intListProperty = (List) value;
      } else {
        if (this.intListProperty == null) this.intListProperty = new ArrayList<>();
        this.intListProperty.add((Integer) value);
      }
    } else {
      throw new RuntimeException("property with key=" + key + " not (yet) supported by " + this.getClass().getName());
    }
    return property(key);
  }

  @Override
  protected void removeSpecificProperty(String key) {
    if (STRING_PROPERTY.equals(key)) {
      this.stringProperty = null;
    } else if (STRING_LIST_PROPERTY.equals(key)) {
      this.stringListProperty = null;
    } else if (INT_PROPERTY.equals(key)) {
      this.intProperty = null;
    } else if (INT_LIST_PROPERTY.equals(key)) {
      this.intListProperty = null;
    } else {
      throw new RuntimeException("property with key=" + key + " not (yet) supported by " + this.getClass().getName());
    }
  }

  private static NodeLayoutInformation layoutInformation = new NodeLayoutInformation(
      new HashSet<>(Arrays.asList(STRING_PROPERTY, INT_PROPERTY, STRING_LIST_PROPERTY, INT_LIST_PROPERTY)),
      Arrays.asList(OdbTestEdge.layoutInformation),
      Arrays.asList(OdbTestEdge.layoutInformation));

  public static NodeFactory<OdbTestNode> factory = new NodeFactory<OdbTestNode>() {

    @Override
    public String forLabel() {
      return OdbTestNode.LABEL;
    }

    @Override
    public OdbTestNode createNode(NodeRef<OdbTestNode> ref) {
      return new OdbTestNode(ref);
    }

    @Override
    public NodeRef<OdbTestNode> createNodeRef(OdbGraph graph, long id) {
      return new NodeRef(graph, id) {
        @Override
        public String label() {
          return OdbTestNode.LABEL;
        }
      };
    }
  };

}